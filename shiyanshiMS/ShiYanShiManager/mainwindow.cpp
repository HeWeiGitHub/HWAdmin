#include "mainwindow.h"
#include "ui_mainwindow.h"

quint32 MainWindow::h=0;
QString MainWindow::exportStr="";

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
/*
    ui->uLabel->setText("用户未登录");
    ui->statLabel->setText("未连接");
*/
    init();

    ui->mainToolBar->addAction(ui->currentUser);

    ui->mainToolBar->addSeparator();
    ui->mainToolBar->addSeparator();
    ui->mainToolBar->addSeparator();

    ui->mainToolBar->addAction(ui->btnSelAll);
    ui->mainToolBar->addAction(ui->btnSel);
    ui->mainToolBar->addAction(ui->btnDel);
    ui->mainToolBar->addAction(ui->btnAdd);

    ui->mainToolBar->addSeparator();
    ui->mainToolBar->addSeparator();
    ui->mainToolBar->addSeparator();

    ui->mainToolBar->addAction(ui->BfAll);
    ui->mainToolBar->addAction(ui->SelBf);
    ui->mainToolBar->addAction(ui->DelBf);
    ui->mainToolBar->addAction(ui->AddBf);

    ui->mainToolBar->addSeparator();
    ui->mainToolBar->addSeparator();
    ui->mainToolBar->addSeparator();

    ui->mainToolBar->addAction(ui->WxAll);
    ui->mainToolBar->addAction(ui->SelWx);
    ui->mainToolBar->addAction(ui->DelWx);
    ui->mainToolBar->addAction(ui->AddWx);

    ui->mainToolBar->addSeparator();
    ui->mainToolBar->addSeparator();
    ui->mainToolBar->addSeparator();

    ui->mainToolBar->addAction(ui->exportWx);
    ui->mainToolBar->addAction(ui->exportBf);
    ui->mainToolBar->addAction(ui->exportSb);



    ui->lcdServTime->setDigitCount(20);

    inupDlg=new InUpDialog();
    sedeDlg=new SeDeDialog();
    lg=new Login();
    servLogin=new ServLogin();
    servLogin->show();
    wx=new WeiXiu();
    bf=new BaoFei();
    aboutDlg=new AboutDialog();
    userdlg=new UserDlg();

    connect(ui->Alluser,SIGNAL(triggered(bool)),this,SLOT(allUser()));
    connect(ui->zhuxiao,SIGNAL(triggered(bool)),this,SLOT(delUser()));
    connect(userdlg,SIGNAL(sendAdd(User)),this,SLOT(addUser(User)));
    connect(ui->currentUser,SIGNAL(triggered(bool)),this,SLOT(currentUser()));
    connect(ui->exportSb,SIGNAL(triggered(bool)),this,SLOT(exportSb()));
    connect(ui->exportWx,SIGNAL(triggered(bool)),this,SLOT(exportWx()));
    connect(ui->exportBf,SIGNAL(triggered(bool)),this,SLOT(exportBf()));
    connect(this,SIGNAL(exportInfo(QString)),this,SLOT(exportFile(QString)));
    connect(ui->about,SIGNAL(triggered(bool)),this,SLOT(showAbout()));
    connect(ui->exit,SIGNAL(triggered(bool)),this,SLOT(myQuit()));

    connect(sedeDlg,SIGNAL(sel(QString)),this,SLOT(getSb(QString)));
    connect(sedeDlg,SIGNAL(del(QString)),this,SLOT(delSb(QString)));
    connect(sedeDlg,SIGNAL(selWx(QString)),this,SLOT(getWx(QString)));
    connect(sedeDlg,SIGNAL(delWx(QString)),this,SLOT(delWx(QString)));
    connect(sedeDlg,SIGNAL(selBf(QString)),this,SLOT(getBf(QString)));
    connect(sedeDlg,SIGNAL(delBf(QString)),this,SLOT(delBf(QString)));

    connect(inupDlg,SIGNAL(in(Shebei)),this,SLOT(addSb(Shebei)));
    connect(inupDlg,SIGNAL(up(Shebei)),this,SLOT(upSb(Shebei)));
    connect(wx,SIGNAL(inWx(Wx)),this,SLOT(addWx(Wx)));
    connect(wx,SIGNAL(upWx(Wx)),this,SLOT(upWx(Wx)));
    connect(bf,SIGNAL(inBf(Bf)),this,SLOT(addBf(Bf)));
    connect(bf,SIGNAL(upBf(Bf)),this,SLOT(upBf(Bf)));

    connect(lg,SIGNAL(sendLg(QString,QString)),this,SLOT(uLogin(QString,QString)));
    connect(servLogin,SIGNAL(servLg(QString,quint32)),this,SLOT(sLogin(QString,quint32)));

    connect(ui->zhuce,SIGNAL(triggered(bool)),this,SLOT(showUserDlg()));
    connect(ui->btnAdd,SIGNAL(triggered(bool)),this,SLOT(showInUp()));
    connect(ui->btnDel,SIGNAL(triggered(bool)),this,SLOT(showSeDe()));
    connect(ui->btnSel,SIGNAL(triggered(bool)),this,SLOT(showSeDe()));
    connect(ui->btnUpdate,SIGNAL(triggered(bool)),this,SLOT(showInUp()));
    connect(ui->btnSelAll,SIGNAL(triggered(bool)),this,SLOT(allSb()));

    connect(ui->WxAll,SIGNAL(triggered(bool)),this,SLOT(allWx()));
    connect(ui->BfAll,SIGNAL(triggered(bool)),this,SLOT(allBf()));

    connect(ui->AddWx,SIGNAL(triggered(bool)),this,SLOT(showWx()));
    connect(ui->UpWx,SIGNAL(triggered(bool)),this,SLOT(showWx()));
    connect(ui->DelWx,SIGNAL(triggered(bool)),this,SLOT(showSeDe()));
    connect(ui->SelWx,SIGNAL(triggered(bool)),this,SLOT(showSeDe()));

    connect(ui->AddBf,SIGNAL(triggered(bool)),this,SLOT(showBf()));
    connect(ui->UpBf,SIGNAL(triggered(bool)),this,SLOT(showBf()));
    connect(ui->DelBf,SIGNAL(triggered(bool)),this,SLOT(showSeDe()));
    connect(ui->SelBf,SIGNAL(triggered(bool)),this,SLOT(showSeDe()));

    connect(ui->login,SIGNAL(triggered(bool)),this,SLOT(showLg()));
    connect(ui->loginout,SIGNAL(triggered(bool)),this,SLOT(uLoginOut()));

}

MainWindow::~MainWindow()
{
    delete ui;
    delete sedeDlg;
    delete inupDlg;
    delete lg;

}

void MainWindow::showAbout()
{
    aboutDlg->show();
}

void MainWindow::showUserDlg()
{
    userdlg->show();
}

void MainWindow::currentUser()
{
    if(user.getUname()=="")
    {
        QMessageBox::critical(this,QStringLiteral("提示"),QStringLiteral("请先登录！"),QMessageBox::Yes);
    }
    else
    {
        QString info=QStringLiteral("姓名：")+user.getUname()+"\n"+QStringLiteral("权限：")+user.getUtype()+"\n"+
                QStringLiteral("电话：")+"\n"+user.getUphone();
        QMessageBox::information(this,QStringLiteral("提示"),info,QMessageBox::Yes);
    }
}

void MainWindow::showInUp()
{
    if(user.getUname()=="")
    {
        QMessageBox::critical(this,QStringLiteral("提示"),QStringLiteral("请先登录！"),QMessageBox::Yes);
    }
    else
    {
        this->inupDlg->show();
    }
}

void MainWindow::showWx()
{
    if(user.getUname()=="")
    {
        QMessageBox::critical(this,QStringLiteral("提示"),QStringLiteral("请先登录！"),QMessageBox::Yes);
    }
    else
    {
        this->wx->show();
    }
}

void MainWindow::showBf()
{
    if(user.getUname()=="")
    {
        QMessageBox::critical(this,QStringLiteral("提示"),QStringLiteral("请先登录！"),QMessageBox::Yes);
    }
    else
    {
        this->bf->show();
    }
}

void MainWindow::showSeDe()
{
    if(user.getUname()=="")
    {
        QMessageBox::information(this,QStringLiteral("提示"),QStringLiteral("请先登录！"),QMessageBox::Yes);
    }
    else
    {
        this->sedeDlg->show();
    }

}

void MainWindow::showLg()
{
    this->lg->show();
}

/******显示用户信息*******************************************************************/
void MainWindow::showUserResult(QStandardItemModel *Model)
{

    Model->setHeaderData(0,Qt::Horizontal,QStringLiteral("用户ID"));
    Model->setHeaderData(1,Qt::Horizontal,QStringLiteral("用户姓名"));
    Model->setHeaderData(2,Qt::Horizontal,QStringLiteral("用户电话"));
    Model->setHeaderData(3,Qt::Horizontal,QStringLiteral("用户权限"));
    ui->tableView->setModel(Model);
    //列宽在设置model之后设置
    ui->tableView->setColumnWidth(0,250);
    ui->tableView->setColumnWidth(1,250);
    ui->tableView->setColumnWidth(2,250);
    ui->tableView->setColumnWidth(3,250);

//    ui->tableView->resizeColumnsToContents();//设置tableView的列宽适应内容的宽
    ui->tableView->show();
}


/******显示设备信息*******************************************************************/
void MainWindow::showResult(QStandardItemModel *Model)
{

    Model->setHeaderData(0,Qt::Horizontal,QStringLiteral("设备ID"));
    Model->setHeaderData(1,Qt::Horizontal,QStringLiteral("设备名称"));
    Model->setHeaderData(2,Qt::Horizontal,QStringLiteral("设备类型"));
    Model->setHeaderData(3,Qt::Horizontal,QStringLiteral("设备产地"));
    Model->setHeaderData(4,Qt::Horizontal,QStringLiteral("设备数量"));
    Model->setHeaderData(5,Qt::Horizontal,QStringLiteral("设备供应商"));
    Model->setHeaderData(6,Qt::Horizontal,QStringLiteral("设备价格"));
    Model->setHeaderData(7,Qt::Horizontal,QStringLiteral("购进日期"));
    Model->setHeaderData(8,Qt::Horizontal,QStringLiteral("有效期至"));

    ui->tableView->setModel(Model);

    //列宽在设置model之后设置
    ui->tableView->setColumnWidth(0,50);
    ui->tableView->setColumnWidth(1,150);
    ui->tableView->setColumnWidth(2,100);
    ui->tableView->setColumnWidth(3,200);
    ui->tableView->setColumnWidth(4,100);
    ui->tableView->setColumnWidth(5,250);
    ui->tableView->setColumnWidth(6,100);
    ui->tableView->setColumnWidth(7,100);
    ui->tableView->setColumnWidth(8,100);

//    ui->tableView->resizeColumnsToContents();//设置tableView的列宽适应内容的宽
    ui->tableView->show();
}

/******显示维修信息*******************************************************************/
void MainWindow::showWxResult(QStandardItemModel *Model)
{

    Model->setHeaderData(0,Qt::Horizontal,QStringLiteral("设备ID"));
    Model->setHeaderData(1,Qt::Horizontal,QStringLiteral("设备名称"));
    Model->setHeaderData(2,Qt::Horizontal,QStringLiteral("损坏原因"));
    Model->setHeaderData(3,Qt::Horizontal,QStringLiteral("负责人"));
    Model->setHeaderData(4,Qt::Horizontal,QStringLiteral("维修单位"));
    Model->setHeaderData(5,Qt::Horizontal,QStringLiteral("维修地点"));
    Model->setHeaderData(6,Qt::Horizontal,QStringLiteral("备注"));
    Model->setHeaderData(7,Qt::Horizontal,QStringLiteral("维修日期"));
    Model->setHeaderData(8,Qt::Horizontal,QStringLiteral("审核人"));
    ui->tableView->setModel(Model);

    //列宽在设置model之后设置

    ui->tableView->setColumnWidth(0,50);
    ui->tableView->setColumnWidth(1,100);
    ui->tableView->setColumnWidth(2,200);
    ui->tableView->setColumnWidth(3,100);
    ui->tableView->setColumnWidth(4,100);
    ui->tableView->setColumnWidth(5,200);
    ui->tableView->setColumnWidth(6,200);
    ui->tableView->setColumnWidth(7,100);
    ui->tableView->setColumnWidth(8,100);

//    ui->tableView->resizeColumnsToContents();//设置tableView的列宽适应内容的宽
    ui->tableView->show();
}


/******显示报废信息*******************************************************************/
void MainWindow::showBfResult(QStandardItemModel *Model)
{

    Model->setHeaderData(0,Qt::Horizontal,QStringLiteral("设备ID"));
    Model->setHeaderData(1,Qt::Horizontal,QStringLiteral("设备名称"));
    Model->setHeaderData(2,Qt::Horizontal,QStringLiteral("设备类型"));
    Model->setHeaderData(3,Qt::Horizontal,QStringLiteral("负责人"));
    Model->setHeaderData(4,Qt::Horizontal,QStringLiteral("备注"));
    Model->setHeaderData(5,Qt::Horizontal,QStringLiteral("报废日期"));
    Model->setHeaderData(6,Qt::Horizontal,QStringLiteral("存放地点"));
    Model->setHeaderData(7,Qt::Horizontal,QStringLiteral("审核人"));
    ui->tableView->setModel(Model);

    //列宽在设置model之后设置
    ui->tableView->setColumnWidth(0,100);
    ui->tableView->setColumnWidth(1,200);
    ui->tableView->setColumnWidth(2,100);
    ui->tableView->setColumnWidth(3,100);
    ui->tableView->setColumnWidth(4,200);
    ui->tableView->setColumnWidth(5,100);
    ui->tableView->setColumnWidth(6,200);
    ui->tableView->setColumnWidth(7,100);

//    ui->tableView->resizeColumnsToContents();//设置tableView的列宽适应内容的宽
    ui->tableView->show();
}

void MainWindow::uLogin(QString uname, QString upass)
{
    QJsonObject json;
    json.insert("sql","uLogin");
    json.insert("uname",uname);
    json.insert("upass",upass);
    sendInfo(json);
}

void MainWindow::uLoginOut()
{
    user.setUname("");
    itemModel->clear();
    ui->uLabel->setText("");
}

void MainWindow::sLogin(QString ip, quint32 port)
{
    newTCPConnection(ip,port);
}

/******用户信息**************************************************************/
void MainWindow::addUser(User u)
{
    if(user.getUname().isEmpty())
    {
        QMessageBox::critical(this,QStringLiteral("提示"),QStringLiteral("请先登录！"),QMessageBox::Yes);
    }
    else if(user.getUtype()!=QStringLiteral("管理员"))
    {
        QMessageBox::critical(this,QStringLiteral("提示"),QStringLiteral("权限不够！"),QMessageBox::Yes);
    }
    else
    {
        QJsonObject json;
        json.insert("sql","addUser");
        json.insert("uid",u.getUid());
        json.insert("uname",u.getUname());
        json.insert("upass",u.getUpass());
        json.insert("uphone",u.getUphone());
        json.insert("utype",u.getUtype());
        sendInfo(json);
    }

}

void MainWindow::delUser()
{
    if(user.getUname().isEmpty())
    {
        QMessageBox::critical(this,QStringLiteral("提示"),QStringLiteral("请先登录！"),QMessageBox::Yes);
    }
    else if(user.getUtype()!=QStringLiteral("管理员"))
    {
        QMessageBox::critical(this,QStringLiteral("提示"),QStringLiteral("权限不够！"),QMessageBox::Yes);
    }
    else
    {
        bool ok;
        QString uname=QInputDialog::getText(this,QStringLiteral("注销用户"),QStringLiteral("用户名："),QLineEdit::Normal,QDir::home().dirName(),&ok);
        if(ok&&!uname.isEmpty())
        {
            QJsonObject json;
            json.insert("sql","delUser");
            json.insert("uname",uname);
            sendInfo(json);
        }
    }
}

void MainWindow::allUser()
{

    if(user.getUname().isEmpty())
    {
        QMessageBox::critical(this,QStringLiteral("提示"),QStringLiteral("请先登录！"),QMessageBox::Yes);
    }
    else if(user.getUtype()!=QStringLiteral("管理员"))
    {
        QMessageBox::critical(this,QStringLiteral("提示"),QStringLiteral("权限不够！"),QMessageBox::Yes);
    }
    else
    {
        itemModel->clear();
        QJsonObject json;
        json.insert("sql","allUser");
        sendInfo(json);
    }
}

/******发送设备请求信息**************************************************************/
void MainWindow::getSb(QString sbname)
{
    itemModel->clear();
    QJsonObject json;
    json.insert("sql","getSb");
    json.insert("sbname",sbname);
    sendInfo(json);
}

void MainWindow::addSb(Shebei sb)
{
    QJsonObject json;
    json.insert("sql","addSb");
    json.insert("sbid",sb.getSbid());
    json.insert("sbname",sb.getSbname());
    json.insert("sbtype",sb.getSbtype());
    json.insert("sbchandi",sb.getSbchandi());
    json.insert("sbcount",sb.getSbcount());
    json.insert("sbprovider",sb.getSbprovider());
    json.insert("sbprice",sb.getSbprice());
    sendInfo(json);
}

void MainWindow::delSb(QString sbname)
{
    QJsonObject json;

    json.insert("sql","delSb");
    json.insert("sbname",sbname);
    sendInfo(json);
}

void MainWindow::upSb(Shebei sb)
{
    QJsonObject json;

    json.insert("sql","upSb");
    json.insert("sbid",sb.getSbid());
    json.insert("sbname",sb.getSbname());
    json.insert("sbtype",sb.getSbtype());
    json.insert("sbchandi",sb.getSbchandi());
    json.insert("sbcount",sb.getSbcount());
    json.insert("sbprovider",sb.getSbprovider());
    json.insert("sbprice",sb.getSbprice());
    sendInfo(json);
}

void MainWindow::allSb()
{

    if(user.getUname()=="")
    {
        QMessageBox::critical(this,QStringLiteral("提示"),QStringLiteral("请先登录！"),QMessageBox::Yes);
    }
    else
    {
        itemModel->clear();
        QJsonObject json;
        json.insert("sql","allSb");
        sendInfo(json);
    }

}

/******发送维修请求信息**************************************************************/
void MainWindow::getWx(QString sbname)
{
    itemModel->clear();
    QJsonObject json;
    json.insert("sql","getWx");
    json.insert("sbname",sbname);
    sendInfo(json);
}

void MainWindow::addWx(Wx wx)
{
    QJsonObject json;
    json.insert("sql","addWx");
    json.insert("Wxid",wx.Wxid);
    json.insert("sbname",wx.sbname);
    json.insert("yuanyin",wx.yuanyin);
    json.insert("fuzeren",wx.fuzeren);
    json.insert("wxdanwei",wx.wxdanwei);
    json.insert("wxdidian",wx.wxdidian);
    json.insert("wxbeizhu",wx.wxbeizhu);
    json.insert("wxdate",wx.wxdate);
    json.insert("wxshenheren",wx.wxshenheren);
    sendInfo(json);
}

void MainWindow::delWx(QString sbname)
{
    QJsonObject json;

    json.insert("sql","delWx");
    json.insert("sbname",sbname);
    sendInfo(json);
}

void MainWindow::upWx(Wx wx)
{
    QJsonObject json;

    json.insert("sql","upWx");
    json.insert("Wxid",wx.Wxid);
    json.insert("sbname",wx.sbname);
    json.insert("yuanyin",wx.yuanyin);
    json.insert("fuzeren",wx.fuzeren);
    json.insert("wxdanwei",wx.wxdanwei);
    json.insert("wxdidian",wx.wxdidian);
    json.insert("wxbeizhu",wx.wxbeizhu);
    json.insert("wxdate",wx.wxdate);
    json.insert("wxshenheren",wx.wxshenheren);
    sendInfo(json);
}

void MainWindow::allWx()
{

    if(user.getUname()=="")
    {
        QMessageBox::critical(this,QStringLiteral("提示"),QStringLiteral("请先登录！"),QMessageBox::Yes);
    }
    else
    {
        itemModel->clear();
        QJsonObject json;
        json.insert("sql","allWx");
        sendInfo(json);
    }

}

/******发送报废请求信息**************************************************************/
void MainWindow::getBf(QString sbname)
{
    itemModel->clear();
    QJsonObject json;
    json.insert("sql","getBf");
    json.insert("sbname",sbname);
    sendInfo(json);
}

void MainWindow::addBf(Bf bf)
{
    QJsonObject json;
    json.insert("sql","addBf");
    json.insert("bfid",bf.bfid);
    json.insert("sbname",bf.sbname);
    json.insert("sbtype",bf.sbtype);
    json.insert("fuzeren",bf.fuzeren);
    json.insert("bfbeizhu",bf.bfbeizhu);
    json.insert("bfdate",bf.bfdate);
    json.insert("cunfangdidian",bf.cunfangdidian);
    json.insert("bfshenheren",bf.bfshenheren);
    sendInfo(json);
}

void MainWindow::delBf(QString sbname)
{
    QJsonObject json;

    json.insert("sql","delBf");
    json.insert("sbname",sbname);
    sendInfo(json);
}

void MainWindow::upBf(Bf bf)
{
    QJsonObject json;

    json.insert("sql","upBf");

    json.insert("bfid",bf.bfid);
    json.insert("sbname",bf.sbname);
    json.insert("sbtype",bf.sbtype);
    json.insert("fuzeren",bf.fuzeren);
    json.insert("bfbeizhu",bf.bfbeizhu);
    json.insert("bfdate",bf.bfdate);
    json.insert("cunfangdidian",bf.cunfangdidian);
    json.insert("bfshenheren",bf.bfshenheren);
    sendInfo(json);
}

void MainWindow::allBf()
{

    if(user.getUname()=="")
    {
        QMessageBox::critical(this,QStringLiteral("提示"),QStringLiteral("请先登录！"),QMessageBox::Yes);
    }
    else
    {
        itemModel->clear();
        QJsonObject json;
        json.insert("sql","allBf");
        sendInfo(json);
    }

}

/******导出数据的请求信息**************************************************************/

void MainWindow::exportSb()
{

    if(user.getUname()=="")
    {
        QMessageBox::critical(this,QStringLiteral("提示"),QStringLiteral("请先登录！"),QMessageBox::Yes);
    }
    else
    {
        QJsonObject json;
        json.insert("sql","exportSb");
        sendInfo(json);
    }

}

void MainWindow::exportWx()
{

    if(user.getUname()=="")
    {
        QMessageBox::critical(this,QStringLiteral("提示"),QStringLiteral("请先登录！"),QMessageBox::Yes);
    }
    else
    {
        QJsonObject json;
        json.insert("sql","exportWx");
        sendInfo(json);
    }

}

void MainWindow::exportBf()
{

    if(user.getUname()=="")
    {
        QMessageBox::critical(this,QStringLiteral("提示"),QStringLiteral("请先登录！"),QMessageBox::Yes);
    }
    else
    {
        QJsonObject json;
        json.insert("sql","exportBf");
        sendInfo(json);
    }

}






// QStringLiteral 这是字符串宏，解决编码问题


//网络连接部分
void MainWindow::init()
{
    tcpSocket=new QTcpSocket(this);

   // newTCPConnection();

    connect(tcpSocket,SIGNAL(readyRead()),this,SLOT(recvData()));
    connect(ui->sendButton,SIGNAL(clicked(bool)),this,SLOT(sendData()));
    connect(tcpSocket,SIGNAL(error(QAbstractSocket::SocketError)),this,SLOT(displayError(QAbstractSocket::SocketError)));
}

void MainWindow::newTCPConnection(QString ip,quint32 port)
{
    tcpSocket->abort();
    tcpSocket->connectToHost(ip,port);
    bool connected=tcpSocket->waitForConnected(timeout);
    if(!connected)
    {
        QMessageBox::critical(this,QStringLiteral("提示"),QStringLiteral("连接超时！"),QMessageBox::Yes);
        return;
    }
    else
    {
        QMessageBox::critical(this,QStringLiteral("提示"),QStringLiteral("连接成功！"),QMessageBox::Yes);
        servLogin->close();
        ui->statLabel->setText(QString("serverIp:%1").arg(ip));
        this->show();

    }
}

void MainWindow::sendData()
{
    if(user.getUname()=="")
    {
        QMessageBox::critical(this,QStringLiteral("提示"),QStringLiteral("请先登录！"),QMessageBox::Yes);
    }
    else
    {
        QString data=ui->chat->toPlainText();
        QJsonObject json;
        json.insert("message",data);
        sendInfo(json);
    }

}

void MainWindow::recvData()
{
    QDataStream in(tcpSocket);
    in.setVersion(QDataStream::Qt_5_5);
    QByteArray byteArray;

    in>>byteArray;

    qDebug()<<"client recive:"+byteArray+"\n";

    QSqlRecord record;
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
            if(obj.contains("time"))
            {
                QJsonValue value = obj.take("time");
                if(value.isString())
                {
                     hello= value.toString();
                     ui->lcdServTime->display(hello);
                }
            }
            if(obj.contains("serverError"))
            {
                QJsonValue value = obj.take("serverError");
                if(value.isString())
                {
                     hello= value.toString();
                     QMessageBox::critical(this,QStringLiteral("serverError"),hello,QMessageBox::Yes);
                }
            }
            if(obj.contains("result"))
            {
                QJsonValue value = obj.take("result");
                if(value.isString())
                {
                     hello= value.toString();
                     if(hello=="addSucceed")
                     {
                         QMessageBox::critical(this,QStringLiteral("操作提示"),QStringLiteral("添加成功！"),QMessageBox::Yes);
                     }
                     if(hello=="delSucceed")
                     {
                         QMessageBox::critical(this,QStringLiteral("操作提示"),QStringLiteral("删除成功！"),QMessageBox::Yes);
                     }
                     if(hello=="updateSucceed")
                     {
                         QMessageBox::critical(this,QStringLiteral("操作提示"),QStringLiteral("修改成功！"),QMessageBox::Yes);
                     }

                }
            }
            if(obj.contains("uLogin"))
            {
                QJsonValue value = obj.take("uLogin");
                if(value.isString())
                {
                     hello= value.toString();
                     if(hello=="succeed")
                     {
                         QMessageBox::critical(this,QStringLiteral("登录提示"),QStringLiteral("登录成功！"),QMessageBox::Yes);
                         lg->close();
                         user.setUId(obj.take("uid").toInt());
                         user.setUname(obj.take("uname").toString());
                         user.setUpass(obj.take("upass").toString());
                         user.setUphone(obj.take("uphone").toString());
                         user.setUtype(obj.take("utype").toString());
                         ui->uLabel->setText(user.getUname());
                     }

                }
            }
            /*******用户信息处理**********************************************************/
            if(obj.contains("allUser"))
            {
                QJsonValue value = obj.take("allUser");
                if(value.isString())
                {
                     hello= value.toString();
                     if(hello=="start")
                     {
                         QJsonValue id = obj.take("uid");
                         QJsonValue name = obj.take("uname");
                         QJsonValue upass = obj.take("upass");
                         QJsonValue uphone = obj.take("uphone");
                         QJsonValue utype = obj.take("utype");

                         QStandardItem *id_item= new QStandardItem(id.toString());
                         QStandardItem *name_item= new QStandardItem(name.toString());
                         QStandardItem *pass_item= new QStandardItem(upass.toString());
                         QStandardItem *uphone_item= new QStandardItem(uphone.toString());
                         QStandardItem *utype_item= new QStandardItem(utype.toString());

                         itemModel->setItem(h, 0, id_item);
                         itemModel->setItem(h, 1, name_item);
                         itemModel->setItem(h, 2, uphone_item);
                         itemModel->setItem(h, 3, utype_item);
                         h++;
                     }
                     if(hello=="complate")
                     {
                         showUserResult(itemModel);
                         h=0;
                     }
                }
            }
            /*******设备信息处理**********************************************************/
            if(obj.contains("allSb"))
            {
                QJsonValue value = obj.take("allSb");
                if(value.isString())
                {
                     hello= value.toString();
                     if(hello=="start")
                     {
                         QJsonValue id = obj.take("sbid");
                         QJsonValue name = obj.take("sbname");
                         QJsonValue type = obj.take("sbtype");
                         QJsonValue chandi = obj.take("sbchandi");
                         QJsonValue count = obj.take("sbcount");
                         QJsonValue provider = obj.take("sbprovider");
                         QJsonValue price = obj.take("sbprice");
                         QJsonValue date = obj.take("sbdate");
                         QJsonValue timeout = obj.take("sbtimeout");

                         QStandardItem *id_item= new QStandardItem(id.toString());
                         QStandardItem *name_item= new QStandardItem(name.toString());
                         QStandardItem *type_item= new QStandardItem(type.toString());
                         QStandardItem *chandi_item= new QStandardItem(chandi.toString());
                         QStandardItem *count_item= new QStandardItem(count.toString());
                         QStandardItem *provider_item= new QStandardItem(provider.toString());
                         QStandardItem *price_item= new QStandardItem(price.toString());
                         QStandardItem *date_item= new QStandardItem(date.toString());
                         QStandardItem *timeout_item= new QStandardItem(timeout.toString());

                         itemModel->setItem(h, 0, id_item);
                         itemModel->setItem(h, 1, name_item);
                         itemModel->setItem(h, 2, type_item);
                         itemModel->setItem(h, 3, chandi_item);
                         itemModel->setItem(h, 4, count_item);
                         itemModel->setItem(h, 5, provider_item);
                         itemModel->setItem(h, 6, price_item);
                         itemModel->setItem(h, 7, date_item);
                         itemModel->setItem(h, 8, timeout_item);

                         h++;
                     }
                     if(hello=="complate")
                     {
                         showResult(itemModel);
                         h=0;
                     }
                }
            }
            if(obj.contains("exportSb"))
            {
                QJsonValue value = obj.take("exportSb");
                if(value.isString())
                {
                     hello= value.toString();
                     if(hello=="start")
                     {
                         QJsonValue id = obj.take("sbid");
                         QJsonValue name = obj.take("sbname");
                         QJsonValue type = obj.take("sbtype");
                         QJsonValue chandi = obj.take("sbchandi");
                         QJsonValue count = obj.take("sbcount");
                         QJsonValue provider = obj.take("sbprovider");
                         QJsonValue price = obj.take("sbprice");
                         QJsonValue date = obj.take("sbdate");
                         QJsonValue timeout = obj.take("sbtimeout");

                         exportStr=exportStr+id.toString()+"    "+name.toString()+"    "+
                                 type.toString()+"    "+chandi.toString()+"    "+count.toString()
                                 +"    "+provider.toString()+"    "+price.toString()+"    "+date.toString()+"    "+timeout.toString()+"\r\n";

                         h++;
                     }
                     if(hello=="complate")
                     {
                         emit exportInfo(QStringLiteral("设备信息.hw"));
                         h=0;
                         exportStr="";

                     }
                }
            }
            if(obj.contains("getSb"))
            {

                QJsonValue value = obj.take("getSb");
                if(value.isString())
                {
                    hello= value.toString();
                     if(hello=="start")
                     {
                         QJsonValue id = obj.take("sbid");
                         QJsonValue name = obj.take("sbname");
                         QJsonValue type = obj.take("sbtype");
                         QJsonValue chandi = obj.take("sbchandi");
                         QJsonValue count = obj.take("sbcount");
                         QJsonValue provider = obj.take("sbprovider");
                         QJsonValue price = obj.take("sbprice");
                         QJsonValue date = obj.take("sbdate");
                         QJsonValue timeout = obj.take("sbtimeout");

                         QStandardItem *id_item= new QStandardItem(id.toString());
                         QStandardItem *name_item= new QStandardItem(name.toString());
                         QStandardItem *type_item= new QStandardItem(type.toString());
                         QStandardItem *chandi_item= new QStandardItem(chandi.toString());
                         QStandardItem *count_item= new QStandardItem(count.toString());
                         QStandardItem *provider_item= new QStandardItem(provider.toString());
                         QStandardItem *price_item= new QStandardItem(price.toString());
                         QStandardItem *date_item= new QStandardItem(date.toString());
                         QStandardItem *timeout_item= new QStandardItem(timeout.toString());

                         itemModel->setItem(h, 0, id_item);
                         itemModel->setItem(h, 1, name_item);
                         itemModel->setItem(h, 2, type_item);
                         itemModel->setItem(h, 3, chandi_item);
                         itemModel->setItem(h, 4, count_item);
                         itemModel->setItem(h, 5, provider_item);
                         itemModel->setItem(h, 6, price_item);
                         itemModel->setItem(h, 7, date_item);
                         itemModel->setItem(h, 8, timeout_item);
                         h++;
                     }
                     if(hello=="complate")
                     {
                         showResult(itemModel);
                         h=0;
                     }
                }
            }

            /*******维修信息处理**********************************************************/
            if(obj.contains("allWx"))
            {
                QJsonValue value = obj.take("allWx");
                if(value.isString())
                {
                     hello= value.toString();
                     if(hello=="start")
                     {
                         QJsonValue id = obj.take("Wxid");
                         QJsonValue name = obj.take("sbname");
                         QJsonValue yuanyin = obj.take("yuanyin");
                         QJsonValue fuzeren = obj.take("fuzeren");
                         QJsonValue wxdanwei = obj.take("wxdanwei");
                         QJsonValue wxdidian = obj.take("wxdidian");
                         QJsonValue wxbeizhu = obj.take("wxbeizhu");
                         QJsonValue wxdate = obj.take("wxdate");
                         QJsonValue wxshenheren = obj.take("wxshenheren");

                         QStandardItem *id_item= new QStandardItem(id.toString());
                         QStandardItem *name_item= new QStandardItem(name.toString());
                         QStandardItem *yuanyin_item= new QStandardItem(yuanyin.toString());
                         QStandardItem *fuzeren_item= new QStandardItem(fuzeren.toString());
                         QStandardItem *wxdanwei_item= new QStandardItem(wxdanwei.toString());
                         QStandardItem *wxdidian_item= new QStandardItem(wxdidian.toString());
                         QStandardItem *wxbeizhu_item= new QStandardItem(wxbeizhu.toString());
                         QStandardItem *wxdate_item= new QStandardItem(wxdate.toString());
                         QStandardItem *wxshenheren_item= new QStandardItem(wxshenheren.toString());

                         itemModel->setItem(h, 0, id_item);
                         itemModel->setItem(h, 1, name_item);
                         itemModel->setItem(h, 2, yuanyin_item);
                         itemModel->setItem(h, 3, fuzeren_item);
                         itemModel->setItem(h, 4, wxdanwei_item);
                         itemModel->setItem(h, 5, wxdidian_item);
                         itemModel->setItem(h, 6, wxbeizhu_item);
                         itemModel->setItem(h, 7, wxdate_item);
                         itemModel->setItem(h, 8, wxshenheren_item);

                         h++;
                     }
                     if(hello=="complate")
                     {
                         showWxResult(itemModel);
                         h=0;
                     }
                }
            }
            if(obj.contains("exportWx"))
            {
                QJsonValue value = obj.take("exportWx");
                if(value.isString())
                {
                     hello= value.toString();
                     if(hello=="start")
                     {
                         QJsonValue id = obj.take("Wxid");
                         QJsonValue name = obj.take("sbname");
                         QJsonValue yuanyin = obj.take("yuanyin");
                         QJsonValue fuzeren = obj.take("fuzeren");
                         QJsonValue wxdanwei = obj.take("wxdanwei");
                         QJsonValue wxdidian = obj.take("wxdidian");
                         QJsonValue wxbeizhu = obj.take("wxbeizhu");
                         QJsonValue wxdate = obj.take("wxdate");
                         QJsonValue wxshenheren = obj.take("wxshenheren");

                         exportStr=exportStr+id.toString()+"    "+name.toString()+"    "+yuanyin.toString()+"    "
                         +fuzeren.toString()+"    "+wxdanwei.toString()+"    "+wxdidian.toString()+"    "+wxbeizhu.toString()+"    "+
                         wxdate.toString()+"    "+wxshenheren.toString()+"\r\n";
                         h++;
                     }
                     if(hello=="complate")
                     {
                         emit exportInfo(QStringLiteral("维修信息.hw"));
                         h=0;
                         exportStr="";

                     }
                }
            }
            if(obj.contains("getWx"))
            {

                QJsonValue value = obj.take("getWx");
                if(value.isString())
                {
                    hello= value.toString();
                     if(hello=="start")
                     {
                         QJsonValue id = obj.take("Wxid");
                         QJsonValue name = obj.take("sbname");
                         QJsonValue yuanyin = obj.take("yuanyin");
                         QJsonValue fuzeren = obj.take("fuzeren");
                         QJsonValue wxdanwei = obj.take("wxdanwei");
                         QJsonValue wxdidian = obj.take("wxdidian");
                         QJsonValue wxbeizhu = obj.take("wxbeizhu");
                         QJsonValue wxdate = obj.take("wxdate");
                         QJsonValue wxshenheren = obj.take("wxshenheren");

                         QStandardItem *id_item= new QStandardItem(id.toString());
                         QStandardItem *name_item= new QStandardItem(name.toString());
                         QStandardItem *yuanyin_item= new QStandardItem(yuanyin.toString());
                         QStandardItem *fuzeren_item= new QStandardItem(fuzeren.toString());
                         QStandardItem *wxdanwei_item= new QStandardItem(wxdanwei.toString());
                         QStandardItem *wxdidian_item= new QStandardItem(wxdidian.toString());
                         QStandardItem *wxbeizhu_item= new QStandardItem(wxbeizhu.toString());
                         QStandardItem *wxdate_item= new QStandardItem(wxdate.toString());
                         QStandardItem *wxshenheren_item= new QStandardItem(wxshenheren.toString());

                         itemModel->setItem(h, 0, id_item);
                         itemModel->setItem(h, 1, name_item);
                         itemModel->setItem(h, 2, yuanyin_item);
                         itemModel->setItem(h, 3, fuzeren_item);
                         itemModel->setItem(h, 4, wxdanwei_item);
                         itemModel->setItem(h, 5, wxdidian_item);
                         itemModel->setItem(h, 6, wxbeizhu_item);
                         itemModel->setItem(h, 7, wxdate_item);
                         itemModel->setItem(h, 8, wxshenheren_item);


                         h++;
                     }
                     if(hello=="complate")
                     {
                         showWxResult(itemModel);
                         h=0;
                     }
                }
            }

            /*******报废信息处理**********************************************************/
            if(obj.contains("allBf"))
            {
                QJsonValue value = obj.take("allBf");
                if(value.isString())
                {
                     hello= value.toString();
                     if(hello=="start")
                     {
                         QJsonValue bfid = obj.take("bfid");
                         QJsonValue name = obj.take("sbname");
                         QJsonValue sbtype = obj.take("sbtype");
                         QJsonValue fuzeren = obj.take("fuzeren");
                         QJsonValue bfbeizhu = obj.take("bfbeizhu");
                         QJsonValue bfdate = obj.take("bfdate");
                         QJsonValue cunfangdidian = obj.take("cunfangdidian");
                         QJsonValue bfshenheren = obj.take("bfshenheren");


                         QStandardItem *id_item= new QStandardItem(bfid.toString());
                         QStandardItem *name_item= new QStandardItem(name.toString());
                         QStandardItem *sbtype_item= new QStandardItem(sbtype.toString());
                         QStandardItem *fuzeren_item= new QStandardItem(fuzeren.toString());
                         QStandardItem *bfbeizhu_item= new QStandardItem(bfbeizhu.toString());
                         QStandardItem *bfdate_item= new QStandardItem(bfdate.toString());
                         QStandardItem *cunfangdidian_item= new QStandardItem(cunfangdidian.toString());
                         QStandardItem *bfshenheren_item= new QStandardItem(bfshenheren.toString());

                         itemModel->setItem(h, 0, id_item);
                         itemModel->setItem(h, 1, name_item);
                         itemModel->setItem(h, 2, sbtype_item);
                         itemModel->setItem(h, 3, fuzeren_item);
                         itemModel->setItem(h, 4, bfbeizhu_item);
                         itemModel->setItem(h, 5, bfdate_item);
                         itemModel->setItem(h, 6, cunfangdidian_item);
                         itemModel->setItem(h, 7, bfshenheren_item);

                         h++;
                     }
                     if(hello=="complate")
                     {
                         showBfResult(itemModel);
                         h=0;
                     }
                }
            }
            if(obj.contains("exportBf"))
            {
                QJsonValue value = obj.take("exportBf");
                if(value.isString())
                {
                     hello= value.toString();
                     if(hello=="start")
                     {
                         QJsonValue bfid = obj.take("bfid");
                         QJsonValue name = obj.take("sbname");
                         QJsonValue sbtype = obj.take("sbtype");
                         QJsonValue fuzeren = obj.take("fuzeren");
                         QJsonValue bfbeizhu = obj.take("bfbeizhu");
                         QJsonValue bfdate = obj.take("bfdate");
                         QJsonValue cunfangdidian = obj.take("cunfangdidian");
                         QJsonValue bfshenheren = obj.take("bfshenheren");

                         exportStr=exportStr+bfid.toString()+"    "+name.toString()+"    "+sbtype.toString()+"    "
                         +fuzeren.toString()+"    "+bfbeizhu.toString()+"    "+bfdate.toString()+"    "
                         +cunfangdidian.toString()+"    "+"    "+bfshenheren.toString()+
                         "\r\n";
                         h++;
                     }
                     if(hello=="complate")
                     {
                         //exportFile(QStringLiteral("报废信息.hw"));
                         emit exportInfo(QStringLiteral("报废信息.hw"));
                         h=0;
                         exportStr="";

                     }
                }
            }
            if(obj.contains("getBf"))
            {

                QJsonValue value = obj.take("getBf");
                if(value.isString())
                {
                    hello= value.toString();
                     if(hello=="start")
                     {
                         QJsonValue bfid = obj.take("bfid");
                         QJsonValue name = obj.take("sbname");
                         QJsonValue sbtype = obj.take("sbtype");
                         QJsonValue fuzeren = obj.take("fuzeren");
                         QJsonValue bfbeizhu = obj.take("bfbeizhu");
                         QJsonValue bfdate = obj.take("bfdate");
                         QJsonValue cunfangdidian = obj.take("cunfangdidian");
                         QJsonValue bfshenheren = obj.take("bfshenheren");


                         QStandardItem *id_item= new QStandardItem(bfid.toString());
                         QStandardItem *name_item= new QStandardItem(name.toString());
                         QStandardItem *sbtype_item= new QStandardItem(sbtype.toString());
                         QStandardItem *fuzeren_item= new QStandardItem(fuzeren.toString());
                         QStandardItem *bfbeizhu_item= new QStandardItem(bfbeizhu.toString());
                         QStandardItem *bfdate_item= new QStandardItem(bfdate.toString());
                         QStandardItem *cunfangdidian_item= new QStandardItem(cunfangdidian.toString());
                         QStandardItem *bfshenheren_item= new QStandardItem(bfshenheren.toString());

                         itemModel->setItem(h, 0, id_item);
                         itemModel->setItem(h, 1, name_item);
                         itemModel->setItem(h, 2, sbtype_item);
                         itemModel->setItem(h, 3, fuzeren_item);
                         itemModel->setItem(h, 4, bfbeizhu_item);
                         itemModel->setItem(h, 5, bfdate_item);
                         itemModel->setItem(h, 6, cunfangdidian_item);
                         itemModel->setItem(h, 7, bfshenheren_item);

                         h++;
                     }
                     if(hello=="complate")
                     {
                         showBfResult(itemModel);
                         h=0;
                     }
                }
            }
        }
    }
    else
    {
        QMessageBox::critical(this,QStringLiteral("提示"),QStringLiteral("接收数据异常"),QMessageBox::Yes);
    }
}

void MainWindow::sendInfo(QJsonObject json)
{
    QJsonDocument doc;
    doc.setObject(json);
    QByteArray bytearray=doc.toJson(QJsonDocument::Compact);
    qDebug()<<"client send:"+bytearray;
    QDataStream out(tcpSocket);
    out.setVersion(QDataStream::Qt_5_5);
    out<<bytearray;
    bool writeAble=out.device()->waitForBytesWritten(timeout);
    if(!writeAble)
    {
        QMessageBox::critical(this,QStringLiteral("提示"),QStringLiteral("发送超时！"),QMessageBox::Yes);
        return;
    }
}


void MainWindow::displayError(QAbstractSocket::SocketError)
{
    QMessageBox::critical(this,QStringLiteral("ServerError"),tcpSocket->errorString(),QMessageBox::Yes);
    tcpSocket->close();
}

void MainWindow::closeEvent(QCloseEvent *event)
{
    QMessageBox::StandardButton tip= QMessageBox::information(this,QStringLiteral("提示"),QStringLiteral("您确定要退出吗？将会关闭连接！"),QMessageBox::Yes|QMessageBox::Abort);

    if(tip == QMessageBox::Abort)
    {
        event->ignore();
    }
    if(tip==QMessageBox::Yes)
    {
        tcpSocket->disconnectFromHost();
        event->accept();
        sedeDlg->close();
        inupDlg->close();
        lg->close();
        wx->close();
        bf->close();
    }
}

void MainWindow::exportFile(QString saveName)
{
    QFileDialog::Options options;
    options|=QFileDialog::DontUseNativeDialog;
    QString selectedFilter;
    QString fileName=QFileDialog::getSaveFileName(this,QStringLiteral("导出数据"),saveName,QObject::tr("hw files (*.hw)"),&selectedFilter,options);
    QFile file(fileName);
    if(file.open(QFile::WriteOnly|QFile::Append))
    {
        QTextStream out(&file);
        out<<exportStr;
    }
}

void MainWindow::myQuit()
{
     this->close();
}


