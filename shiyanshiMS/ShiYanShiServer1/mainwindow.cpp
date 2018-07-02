#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    init();
    adminloginDlg=new AdminLoginDlg();
    adminloginDlg->show();
    connect(adminloginDlg,SIGNAL(sendLg(QString,QString)),this,SLOT(adminlogin(QString,QString)));

    servDT=QDateTime::currentDateTime();
    servDTS=servDT.toString("yyyy-MM-dd hh:mm:ss");
    ui->lcdTimer->setDigitCount(20);
    ui->lcdTimer->display(servDTS);

    ui->ConCount->setText(QString("%1").arg(ConCount));

    servTimer=new QTimer(this);
    connect(servTimer, SIGNAL(timeout()), this, SLOT(refreshTime()));
    servTimer->setInterval(1000);
    servTimer->start();
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::init()
{
    tcpServer=new QTcpServer(this);
    tcpSocket=new QTcpSocket(this);
    HostInof=QStringLiteral("主机名：")+QHostInfo::localHostName()+"\n"+"IP:"+get_localmachine_ip()+"\n";
    ui->hostInfo->setPlainText(HostInof);
    ui->Constatus->setText(QStringLiteral("未连接"));
    newListen();

    connect(tcpServer,SIGNAL(newConnection()),this,SLOT(acceptConnection()));
    connect(ui->sendButton,SIGNAL(clicked(bool)),this,SLOT(sendData()));
    connect(tcpSocket,SIGNAL(error(QAbstractSocket::SocketError)),this,SLOT(displayError(QAbstractSocket::SocketError)));

}

void MainWindow::refreshTime()
{
    servDT=QDateTime::currentDateTime();
    servDTS=servDT.toString("yyyy-MM-dd hh:mm:ss");
    ui->lcdTimer->display(servDTS);

    if(tcpSocket->state()==QAbstractSocket::ConnectedState)
    {
        QJsonObject json;
        json.insert("time",servDTS);
        sendInfo(json);
    }

}

void MainWindow::adminlogin(QString uname, QString upass)
{
    isOk=userdao.uSelect(uname);
    if(!isOk)
    {
        QMessageBox::information(this,QStringLiteral("提示"),userdao.msg,QMessageBox::Yes);
    }
    else if(userdao.uModel->record(0).value("uname").toString()!=uname||uname=="")
    {
        QMessageBox::information(this,QStringLiteral("提示"),QStringLiteral("用户不存在！"),QMessageBox::Yes);
    }
    else if(userdao.uModel->record(0).value("utype").toString()!=QStringLiteral("管理员"))
    {
        QMessageBox::information(this,QStringLiteral("提示"),QStringLiteral("用户不是管理员！"),QMessageBox::Yes);
    }
    else if(userdao.uModel->record(0).value("upass").toString()!=upass)
    {
        QMessageBox::information(this,QStringLiteral("提示"),QStringLiteral("密码错误"),QMessageBox::Yes);
    }
    else
    {
        adminloginDlg->close();
        admin=uname;
        ui->manager->setText(admin);
        this->show();

    }
}

void MainWindow::sendData()
{
    QString data=ui->chat->toPlainText();
    QJsonObject json;
    json.insert("message",data);
    servTimer->stop();
    sendInfo(json);
    servTimer->start();
}

void MainWindow::recvData()
{

    QDataStream in(tcpSocket);
    in.setVersion(QDataStream::Qt_5_5);
    QByteArray byteArray;

    /*
    bool readAble=in.device()->waitForReadyRead(timeout);
    if(!readAble)
    {
        QMessageBox::critical(this,QStringLiteral("提示"),QStringLiteral("读取超时！"),QMessageBox::Yes);
    }
    */
    in>>byteArray;

    qDebug()<<"server recive:"+byteArray+"\n";

    QString hello;
    QJsonParseError json_error;
    QJsonDocument parse_doucment=QJsonDocument::fromJson(byteArray,&json_error);
    if(json_error.error == QJsonParseError::NoError)
    {
        if(parse_doucment.isObject())
        {
            QJsonObject obj = parse_doucment.object();
            if(obj.contains("message"))
            {
                QJsonValue value = obj.take("message");
                if(value.isString())
                {
                     hello= value.toString();
                     ui->chat->setPlainText(hello);
                }
            }
            if(obj.contains("sql"))
            {
                QJsonValue value = obj.take("sql");
                if(value.isString())
                {
                    /*****用户信息处理***************************************************************/
                    QString sql=value.toString();
                    if(sql=="uLogin")
                    {
                        QJsonValue name = obj.take("uname");
                        QJsonValue pass = obj.take("upass");
                        uLogin(name.toString(),pass.toString());
                    }
                    else if(sql=="delUser")
                    {
                        QJsonValue name = obj.take("uname");
                        delUser(name.toString());
                    }
                    else if(sql=="addUser")
                    {
                        User user;
                        QJsonValue id = obj.take("uid");
                        QJsonValue name = obj.take("uname");
                        QJsonValue upass = obj.take("upass");
                        QJsonValue uphone = obj.take("uphone");
                        QJsonValue utype = obj.take("utype");

                        user.setUId(id.toInt());
                        user.setUname(name.toString());
                        user.setUpass(upass.toString());
                        user.setUphone(uphone.toString());
                        user.setUtype(utype.toString());
                        addUser(user);
                    }
                    else if(sql=="allUser")
                    {
                        allUser("allUser");
                    }
                    /*****设备处理***************************************************************/

                    else if(sql=="getSb")
                    {
                        QJsonValue name = obj.take("sbname");
                        getSb(name.toString());
                    }
                    else if(sql=="delSb")
                    {
                        QJsonValue name = obj.take("sbname");
                        delSb(name.toString());
                    }
                    else if(sql=="upSb")
                    {
                        Shebei sb;
                        QJsonValue id = obj.take("sbid");
                        QJsonValue name = obj.take("sbname");
                        QJsonValue type = obj.take("sbtype");
                        QJsonValue chandi = obj.take("sbchandi");
                        QJsonValue count = obj.take("sbcount");
                        QJsonValue provider = obj.take("sbprovider");
                        QJsonValue price = obj.take("sbprice");
                        QJsonValue date = obj.take("sbdate");
                        QJsonValue timeout = obj.take("sbtimeout");
                        sb.setSbId(id.toInt());
                        sb.setSbname(name.toString());
                        sb.setSbtype(type.toString());
                        sb.setSbchandi(chandi.toString());
                        sb.setSbcount(count.toInt());
                        sb.setSbprovider(provider.toString());
                        sb.setSbprice(price.toString());
                        sb.setSbdate(date.toString());
                        sb.setSbtimeout(timeout.toString());
                        upSb(sb);
                    }
                    else if(sql=="addSb")
                    {
                        Shebei sb;
                        QJsonValue id = obj.take("sbid");
                        QJsonValue name = obj.take("sbname");
                        QJsonValue type = obj.take("sbtype");
                        QJsonValue chandi = obj.take("sbchandi");
                        QJsonValue count = obj.take("sbcount");
                        QJsonValue provider = obj.take("sbprovider");
                        QJsonValue price = obj.take("sbprice");
                        QJsonValue date = obj.take("sbdate");
                        QJsonValue timeout = obj.take("sbtimeout");
                        sb.setSbId(id.toInt());
                        sb.setSbname(name.toString());
                        sb.setSbtype(type.toString());
                        sb.setSbchandi(chandi.toString());
                        sb.setSbcount(count.toInt());
                        sb.setSbprovider(provider.toString());
                        sb.setSbprice(price.toString());
                        sb.setSbdate(date.toString());
                        sb.setSbtimeout(timeout.toString());
                        addSb(sb);
                    }
                    else if(sql=="allSb")
                    {
                        allSb("allSb");
                    }
                    else if(sql=="exportSb")
                    {
                        allSb("exportSb");
                    }

                    /****维修记录处理************************************************/

                    else if(sql=="getWx")
                    {
                        QJsonValue name = obj.take("sbname");
                        getWx(name.toString());
                    }
                    else if(sql=="delWx")
                    {
                        QJsonValue name = obj.take("sbname");
                        delWx(name.toString());
                    }
                    else if(sql=="upWx")
                    {
                        Wx wx;
                        QJsonValue id = obj.take("Wxid");
                        QJsonValue name = obj.take("sbname");
                        QJsonValue yuanyin = obj.take("yuanyin");
                        QJsonValue fuzeren = obj.take("fuzeren");
                        QJsonValue wxdanwei = obj.take("wxdanwei");
                        QJsonValue wxdidian = obj.take("wxdidian");
                        QJsonValue wxbeizhu = obj.take("wxbeizhu");
                        QJsonValue wxdate = obj.take("wxdate");
                        QJsonValue wxshenheren = obj.take("wxshenheren");

                        wx.Wxid=id.toInt();
                        wx.sbname=name.toString();
                        wx.yuanyin=yuanyin.toString();
                        wx.fuzeren=fuzeren.toString();
                        wx.wxdanwei=wxdanwei.toString();
                        wx.wxdidian=wxdidian.toString();
                        wx.wxbeizhu=wxbeizhu.toString();
                        wx.wxdate=wxdate.toString();
                        wx.wxshenheren=wxshenheren.toString();
                        upWx(wx);
                    }
                    else if(sql=="addWx")
                    {
                        Wx wx;
                        QJsonValue id = obj.take("Wxid");
                        QJsonValue name = obj.take("sbname");
                        QJsonValue yuanyin = obj.take("yuanyin");
                        QJsonValue fuzeren = obj.take("fuzeren");
                        QJsonValue wxdanwei = obj.take("wxdanwei");
                        QJsonValue wxdidian = obj.take("wxdidian");
                        QJsonValue wxbeizhu = obj.take("wxbeizhu");
                        QJsonValue wxdate = obj.take("wxdate");
                        QJsonValue wxshenheren = obj.take("wxshenheren");

                        wx.Wxid=id.toInt();
                        wx.sbname=name.toString();
                        wx.yuanyin=yuanyin.toString();
                        wx.fuzeren=fuzeren.toString();
                        wx.wxdanwei=wxdanwei.toString();
                        wx.wxdidian=wxdidian.toString();
                        wx.wxbeizhu=wxbeizhu.toString();
                        wx.wxdate=wxdate.toString();
                        wx.wxshenheren=wxshenheren.toString();
                        addWx(wx);
                    }
                    else if(sql=="allWx")
                    {
                        allWx("allWx");
                    }
                    else if(sql=="exportWx")
                    {
                        allWx("exportWx");
                    }

                    /***报废记录处理**********************************************************************/

                    else if(sql=="getBf")
                    {
                        QJsonValue name = obj.take("sbname");
                        getBf(name.toString());
                    }
                    else if(sql=="delBf")
                    {
                        QJsonValue name = obj.take("sbname");
                        delBf(name.toString());
                    }
                    else if(sql=="upBf")
                    {
                        Bf bf;
                        QJsonValue bfid = obj.take("bfid");
                        QJsonValue name = obj.take("sbname");
                        QJsonValue sbtype = obj.take("sbtype");
                        QJsonValue fuzeren = obj.take("fuzeren");
                        QJsonValue bfbeizhu = obj.take("bfbeizhu");
                        QJsonValue bfdate = obj.take("bfdate");
                        QJsonValue cunfangdidian = obj.take("cunfangdidian");
                        QJsonValue bfshenheren = obj.take("bfshenheren");

                        bf.bfid=bfid.toInt();
                        bf.sbname=name.toString();
                        bf.sbtype=sbtype.toString();
                        bf.fuzeren=fuzeren.toString();
                        bf.bfbeizhu=bfbeizhu.toString();
                        bf.bfdate=bfdate.toString();
                        bf.cunfangdidian=cunfangdidian.toString();
                        bf.bfshenheren=bfshenheren.toString();

                        upBf(bf);
                    }
                    else if(sql=="addBf")
                    {
                        Bf bf;
                        QJsonValue bfid = obj.take("bfid");
                        QJsonValue name = obj.take("sbname");
                        QJsonValue sbtype = obj.take("sbtype");
                        QJsonValue fuzeren = obj.take("fuzeren");
                        QJsonValue bfbeizhu = obj.take("bfbeizhu");
                        QJsonValue bfdate = obj.take("bfdate");
                        QJsonValue cunfangdidian = obj.take("cunfangdidian");
                        QJsonValue bfshenheren = obj.take("bfshenheren");

                        bf.bfid=bfid.toInt();
                        bf.sbname=name.toString();
                        bf.sbtype=sbtype.toString();
                        bf.fuzeren=fuzeren.toString();
                        bf.bfbeizhu=bfbeizhu.toString();
                        bf.bfdate=bfdate.toString();
                        bf.cunfangdidian=cunfangdidian.toString();
                        bf.bfshenheren=bfshenheren.toString();
                        addBf(bf);
                    }
                    else if(sql=="allBf")
                    {
                        allBf("allBf");
                    }
                    else if(sql=="exportBf")
                    {
                        allBf("exportBf");
                    }


                }
            }
        }

    }

}

void MainWindow::newListen()
{
    //监听任意ip地址，端口为6666的客户端
    if(!tcpServer->listen(QHostAddress::Any,6666))
    {
        QMessageBox::critical(this,QStringLiteral("ServerError"),tcpServer->errorString(),QMessageBox::Yes);
        close();
        ui->Constatus->setText(QStringLiteral("连接出错！"));
        return;
    }

    /*
    HostInof+=QString("status:%1").arg(QNetworkInterface::IsRunning)+"\n";
    ui->hostInfo->setText(HostInof);
    */
}

void MainWindow::acceptConnection()
{

    tcpSocket=tcpServer->nextPendingConnection();
    connect(tcpSocket,SIGNAL(readyRead()),this,SLOT(recvData()));
    connect(tcpSocket,SIGNAL(disconnected()),this,SLOT(disConnection()));

    HostInof+=QStringLiteral("客户端地址：")+tcpSocket->peerAddress().toString()+"\n"+QStringLiteral("客户端端口：");
    HostInof+=QString("%1\n").arg(tcpSocket->peerPort());
    //设置hostInfo组件（QTextBrower）的内容
    ui->hostInfo->setPlainText(HostInof);
    ui->Constatus->setText(QStringLiteral("连接成功"));

    ConCount++;
    ui->ConCount->setText(QString("%1").arg(ConCount));
    QJsonObject json;
    json.insert("message","hello,this is server");
    sendInfo(json);
}

void MainWindow::displayError(QAbstractSocket::SocketError)
{
    QMessageBox::critical(this,QStringLiteral("ServerError"),tcpSocket->errorString(),QMessageBox::Yes);
    tcpSocket->close();
}

void MainWindow::disConnection()
{
    ConCount--;
    ui->ConCount->setText(QString("%1").arg(ConCount));
    ui->Constatus->setText(QStringLiteral("断开连接"));
    QMessageBox::critical(this,QStringLiteral("提示"),QStringLiteral("断开连接"),QMessageBox::Yes);
}

QString MainWindow::get_localmachine_ip()
{
    QString ipAddress;

    QList<QHostAddress> ipAddressesList = QNetworkInterface::allAddresses();
    // use the first non-localhost IPv4 address
    for (int i = 0; i < ipAddressesList.size(); ++i) {
        if (ipAddressesList.at(i) != QHostAddress::LocalHost &&
            ipAddressesList.at(i).toIPv4Address()) {
            ipAddress = ipAddressesList.at(i).toString();
            break;
        }
    }

    // if we did not find one, use IPv4 localhost
    if (ipAddress.isEmpty())
        ipAddress = QHostAddress(QHostAddress::LocalHost).toString();
    return ipAddress;
}

/***************************设备信息操作*****************************************/

void MainWindow::getSb(QString sbname)
{
    servTimer->stop();
    isOk=sbdao.sbSelect(sbname);
    if(!isOk)
    {
        QJsonObject json;
        json.insert("serverError",sbdao.msg);
        sendInfo(json);
    }
    else
    {
        sendModel(sbdao.sbModel,"getSb");
    }
    servTimer->start();
}

void MainWindow::addSb(Shebei sb)
{
    servTimer->stop();
    isOk=sbdao.sbAdd(sb);
    if(!isOk)
    {
        QJsonObject json;
        json.insert("serverError",sbdao.msg);
        sendInfo(json);
    }
    else
    {
        QJsonObject json;
        json.insert("result","addSucceed");
        sendInfo(json);
    }
    servTimer->start();
}

void MainWindow::delSb(QString sbname)
{
    servTimer->stop();
    isOk=sbdao.sbDelete(sbname);
    if(!isOk)
    {
        QJsonObject json;
        json.insert("serverError",sbdao.msg);
        sendInfo(json);
    }
    else
    {
        QJsonObject json;
        json.insert("result","delSucceed");
        sendInfo(json);
    }
    servTimer->start();
}

void MainWindow::upSb(Shebei sb)
{
    servTimer->stop();
    isOk=sbdao.sbUpdate(sb);
    if(!isOk)
    {
        QJsonObject json;
        json.insert("serverError",sbdao.msg);
        sendInfo(json);
    }
    else
    {
        QJsonObject json;
        json.insert("result","updateSucceed");
        sendInfo(json);
    }
    servTimer->start();
}

void MainWindow::allSb(QString sql)
{
    servTimer->stop();
    isOk=sbdao.sbSelectAll();
    if(!isOk)
    {
        QJsonObject json;
        json.insert("serverError",sbdao.msg);
        sendInfo(json);
    }
    else
    {
        sendModel(sbdao.sbModel,sql);
    }
    servTimer->start();
}

/***************************维修记录操作*****************************************/

void MainWindow::getWx(QString sbname)
{
    servTimer->stop();
    isOk=wxdao.wxSelect(sbname);
    if(!isOk)
    {
        QJsonObject json;
        json.insert("serverError",sbdao.msg);
        sendInfo(json);
    }
    else
    {
        sendWxModel(wxdao.wxModel,"getWx");
    }
    servTimer->start();
}

void MainWindow::addWx(Wx wx)
{
    servTimer->stop();
    isOk=wxdao.wxAdd(wx);
    if(!isOk)
    {
        QJsonObject json;
        json.insert("serverError",wxdao.msg);
        sendInfo(json);
    }
    else
    {
        QJsonObject json;
        json.insert("result","addSucceed");
        sendInfo(json);
    }
    servTimer->start();
}

void MainWindow::delWx(QString sbname)
{
    servTimer->stop();
    isOk=wxdao.wxDelete(sbname);
    if(!isOk)
    {
        QJsonObject json;
        json.insert("serverError",wxdao.msg);
        sendInfo(json);
    }
    else
    {
        QJsonObject json;
        json.insert("result","delSucceed");
        sendInfo(json);
    }
    servTimer->start();
}

void MainWindow::upWx(Wx wx)
{
    servTimer->stop();
    isOk=wxdao.wxUpdate(wx);
    if(!isOk)
    {
        QJsonObject json;
        json.insert("serverError",wxdao.msg);
        sendInfo(json);
    }
    else
    {
        QJsonObject json;
        json.insert("result","updateSucceed");
        sendInfo(json);
    }
    servTimer->start();
}

void MainWindow::allWx(QString sql)
{
    servTimer->stop();
    isOk=wxdao.wxSelectAll();
    if(!isOk)
    {
        QJsonObject json;
        json.insert("serverError",wxdao.msg);
        sendInfo(json);
    }
    else
    {
        sendWxModel(wxdao.wxModel,sql);
    }
    servTimer->start();

}


/***************************报废记录操作*****************************************/

void MainWindow::getBf(QString sbname)
{
    servTimer->stop();
    isOk=bfdao.bfSelect(sbname);
    if(!isOk)
    {
        QJsonObject json;
        json.insert("serverError",bfdao.msg);
        sendInfo(json);
    }
    else
    {
        sendBfModel(bfdao.bfModel,"getBf");
    }
    servTimer->start();
}

void MainWindow::addBf(Bf bf)
{
    servTimer->stop();
    isOk=bfdao.bfAdd(bf);
    if(!isOk)
    {
        QJsonObject json;
        json.insert("serverError",bfdao.msg);
        sendInfo(json);
    }
    else
    {
        QJsonObject json;
        json.insert("result","addSucceed");
        sendInfo(json);
    }
    servTimer->start();
}

void MainWindow::delBf(QString sbname)
{
    servTimer->stop();
    isOk=bfdao.bfDelete(sbname);
    if(!isOk)
    {
        QJsonObject json;
        json.insert("serverError",bfdao.msg);
        sendInfo(json);
    }
    else
    {
        QJsonObject json;
        json.insert("result","delSucceed");
        sendInfo(json);
    }
    servTimer->start();
}

void MainWindow::upBf(Bf bf)
{
    servTimer->stop();
    isOk=bfdao.bfUpdate(bf);
    if(!isOk)
    {
        QJsonObject json;
        json.insert("serverError",bfdao.msg);
        sendInfo(json);
    }
    else
    {
        QJsonObject json;
        json.insert("result","updateSucceed");
        sendInfo(json);
    }
    servTimer->start();
}

void MainWindow::allBf(QString sql)
{
    servTimer->stop();
    isOk=bfdao.bfSelectAll();
    if(!isOk)
    {
        QJsonObject json;
        json.insert("serverError",bfdao.msg);
        sendInfo(json);
    }
    else
    {
        sendBfModel(bfdao.bfModel,sql);
    }
    servTimer->start();
}


/***************************用户操作*****************************************/

void MainWindow::addUser(User user)
{
    servTimer->stop();
    isOk=userdao.uAdd(user);
    if(!isOk)
    {
        QJsonObject json;
        json.insert("serverError",userdao.msg);
        sendInfo(json);
    }
    else
    {
        QJsonObject json;
        json.insert("result","addSucceed");
        sendInfo(json);
    }
    servTimer->start();
}

void MainWindow::delUser(QString uname)
{
    servTimer->stop();
    isOk=userdao.uDelete(uname);
    if(!isOk)
    {
        QJsonObject json;
        json.insert("serverError",bfdao.msg);
        sendInfo(json);
    }
    else
    {
        QJsonObject json;
        json.insert("result","delSucceed");
        sendInfo(json);
    }
    servTimer->start();
}

void MainWindow::allUser(QString sql)
{
    servTimer->stop();
    isOk=userdao.uSelectAll();
    if(!isOk)
    {
        QJsonObject json;
        json.insert("serverError",bfdao.msg);
        sendInfo(json);
    }
    else
    {
        sendUserModel(userdao.uModel,sql);
    }
    servTimer->start();
}


/***********************************************************************************/


void MainWindow::sendInfo(QJsonObject json)
{
    QJsonDocument doc;
    doc.setObject(json);
    QByteArray bytearray=doc.toJson(QJsonDocument::Compact);
    qDebug()<<"server send:"+bytearray;
    QDataStream out(tcpSocket);
    out.setVersion(QDataStream::Qt_5_5);
    out<<bytearray;
    bool writed= out.device()->waitForBytesWritten(timeout);
    if(!writed)
    {
        QMessageBox::critical(this,QStringLiteral("提示"),QStringLiteral("发送超时！"),QMessageBox::Yes);
        return;
    }
}


/*********************************发送设备信息*************************************************/

void MainWindow::sendModel(QSqlQueryModel *queryModel,QString sql)
{
    quint32 i;
    for(i=0;i<queryModel->rowCount();i++)
    {
        QJsonObject json;
        json.insert(sql,"start");
        QSqlRecord record=queryModel->record(i);

        json.insert("sbid",record.value("sbid").toString());
        json.insert("sbname",record.value("sbname").toString());
        json.insert("sbtype",record.value("sbtype").toString());
        json.insert("sbchandi",record.value("sbchandi").toString());
        json.insert("sbcount",record.value("sbcount").toString());
        json.insert("sbprovider",record.value("sbprovider").toString());
        json.insert("sbprice",record.value("sbprice").toString());
        json.insert("sbdate",record.value("sbdate").toString());
        json.insert("sbtimeout",record.value("sbtimeout").toString());
        sendInfo(json);
        delay(delaytime);
    }
    QJsonObject json;
    json.insert(sql,"complate");
    sendInfo(json);
    delay(delaytime);
}

/*********************************发送维修信息*************************************************/

void MainWindow::sendUserModel(QSqlQueryModel *queryModel,QString sql)
{
    quint32 i;
    for(i=0;i<queryModel->rowCount();i++)
    {
        QJsonObject json;

        json.insert(sql,"start");
        QSqlRecord record=queryModel->record(i);
        json.insert("uid",record.value("uid").toString());
        json.insert("uname",record.value("uname").toString());
        json.insert("upass",record.value("upass").toString());
        json.insert("uphone",record.value("uphone").toString());
        json.insert("utype",record.value("utype").toString());
        sendInfo(json);
        delay(delaytime);
    }
    QJsonObject json;
    json.insert(sql,"complate");
    sendInfo(json);
    delay(delaytime);
}

/*********************************发送维修信息*************************************************/

void MainWindow::sendWxModel(QSqlQueryModel *queryModel,QString sql)
{
    quint32 i;
    for(i=0;i<queryModel->rowCount();i++)
    {
        QJsonObject json;

        json.insert(sql,"start");
        QSqlRecord record=queryModel->record(i);

        json.insert("Wxid",record.value("Wxid").toString());
        json.insert("sbname",record.value("sbname").toString());
        json.insert("yuanyin",record.value("yuanyin").toString());
        json.insert("fuzeren",record.value("fuzeren").toString());
        json.insert("wxdanwei",record.value("wxdanwei").toString());
        json.insert("wxdidian",record.value("wxdidian").toString());
        json.insert("wxbeizhu",record.value("wxbeizhu").toString());
        json.insert("wxdate",record.value("wxdate").toString());
        json.insert("wxshenheren",record.value("wxshenheren").toString());

        sendInfo(json);
        delay(delaytime);
    }
    QJsonObject json;
    json.insert(sql,"complate");
    sendInfo(json);
    delay(delaytime);
}

/*********************************发送报废信息*************************************************/

void MainWindow::sendBfModel(QSqlQueryModel *queryModel,QString sql)
{
    quint32 i;
    for(i=0;i<queryModel->rowCount();i++)
    {
        servTimer->stop();
        QJsonObject json;
        json.insert(sql,"start");
        QSqlRecord record=queryModel->record(i);

        json.insert("bfid",record.value("bfid").toString());
        json.insert("sbname",record.value("sbname").toString());
        json.insert("sbtype",record.value("sbtype").toString());
        json.insert("fuzeren",record.value("fuzeren").toString());
        json.insert("bfbeizhu",record.value("bfbeizhu").toString());
        json.insert("bfdate",record.value("bfdate").toString());
        json.insert("cunfangdidian",record.value("cunfangdidian").toString());
        json.insert("bfshenheren",record.value("bfshenheren").toString());
        sendInfo(json);
        delay(delaytime);
    }
    QJsonObject json;
    json.insert(sql,"complate");
    sendInfo(json);
    delay(delaytime);
}

/**********************************************************************************/

void MainWindow::uLogin(QString uname, QString upass)
{

    servTimer->stop();
    isOk=userdao.uSelect(uname);
    if(!isOk)
    {
        QJsonObject json;
        json.insert("serverError",sbdao.msg);
        sendInfo(json);
    }
    else if(userdao.uModel->record(0).value("uname").toString()!=uname||uname=="")
    {
        QJsonObject json;
        json.insert("serverError",QStringLiteral("用户不存在！"));
        sendInfo(json);
    }
    else
    {

        if(upass!=userdao.uModel->record(0).value("upass").toString())
        {
            QJsonObject json;
            json.insert("serverError",QStringLiteral("密码错误！"));
            sendInfo(json);
        }
        else
        {
            QJsonObject json;
            json.insert("uLogin","succeed");
            QSqlRecord record=userdao.uModel->record(0);
            json.insert("uid",record.value("uid").toString());
            json.insert("uname",record.value("uname").toString());
            json.insert("upass",record.value("upass").toString());
            json.insert("uphone",record.value("uphone").toString());
            json.insert("utype",record.value("utype").toString());
            sendInfo(json);

        }
    }
    servTimer->start();
}

void MainWindow::delay(quint32 time)
{
        QTime dieTime = QTime::currentTime().addMSecs(time);
        while( QTime::currentTime() < dieTime )
        QCoreApplication::processEvents(QEventLoop::AllEvents, 100);
}


void MainWindow::closeEvent(QCloseEvent *event)
{
    QMessageBox::StandardButton tip= QMessageBox::information(this,QStringLiteral("提示"),QStringLiteral("您确定要退出吗？将会关闭连接和服务！"),QMessageBox::Yes|QMessageBox::Abort);

    if(tip == QMessageBox::Abort)
    {
        event->ignore();
    }
    if(tip==QMessageBox::Yes)
    {
        tcpSocket->disconnectFromHost();
        event->accept();
    }
}
