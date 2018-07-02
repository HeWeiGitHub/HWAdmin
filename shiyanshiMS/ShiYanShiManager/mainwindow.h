#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include "userdlg.h"
#include "aboutdialog.h"
#include "inupdialog.h"
#include "sededialog.h"
#include "servlogin.h"
#include "weixiu.h"
#include "baofei.h"
#include "login.h"
#include "shebei.h"
#include "user.h"
#include <QFileDialog>
#include <QFile>
#include <QTextStream>
#include <QInputDialog>
#include <QStandardItem>
#include <QStandardItemModel>
#include <QSqlTableModel>
#include <QSqlRecord>
#include <QMainWindow>
#include <QMessageBox>
#include <QtNetwork>
#include <QtNetwork/QTcpSocket>
#include <QCloseEvent>
#include <QJsonObject>
#include <QJsonDocument>


namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();


public:
    Ui::MainWindow* getUi();

public:
    AboutDialog *aboutDlg;
    InUpDialog *inupDlg;
    SeDeDialog *sedeDlg;
    Login *lg;
    UserDlg *userdlg;
    ServLogin *servLogin;
    WeiXiu *wx;
    BaoFei *bf;

    static quint32 h;
    Shebei sb;
    User user;
    QString uname;
//    QSqlQueryModel *queryModel;
    bool isOk;
    QMessageBox::StandardButton tip;

protected:
    void init();
    void newTCPConnection(QString ip,quint32 port);

signals:
    void exportInfo(QString saveName);

public slots:
    void showInUp();
    void showSeDe();
    void showLg();
    void showWx();
    void showBf();
    void showAbout();
    void showUserDlg();

    void showResult(QStandardItemModel *itemModel);
    void showUserResult(QStandardItemModel *itemModel);
    void showWxResult(QStandardItemModel *itemModel);
    void showBfResult(QStandardItemModel *itemModel);

    /******用户信息**********************************/
    void allUser();
    void delUser();
    void addUser(User u);

    /******发送设备信息**********************************/
    void allSb();
    void getSb(QString sbname);
    void delSb(QString sbname);
    void addSb(Shebei sb);
    void upSb(Shebei sb);
    /******发送维修信息**********************************/
    void allWx();
    void getWx(QString sbname);
    void delWx(QString sbname);
    void addWx(Wx wx);
    void upWx(Wx wx);
    /******发送报废信息**********************************/
    void allBf();
    void getBf(QString sbname);
    void delBf(QString sbname);
    void addBf(Bf bf);
    void upBf(Bf bf);

    void uLogin(QString uname,QString upass);
    void sLogin(QString ip,quint32 port);
    void uLoginOut();

    //发送聊天消息
    void sendData();
    //发送操作请求
    void sendInfo(QJsonObject json);
    void recvData();
    void displayError(QAbstractSocket::SocketError);

    //重写默认接口的关闭事件
    void closeEvent(QCloseEvent *event);
    void myQuit();
    void exportSb();
    void exportWx();
    void exportBf();
    void exportFile(QString saveName);

    void currentUser();

private:
    //QSqlTableModel tableModel;
    QStandardItemModel *itemModel=new QStandardItemModel;
    quint32 timeout=5000;
    Ui::MainWindow *ui;
    QTcpSocket *tcpSocket;
    quint32 port;
    QString ip="";
    static QString exportStr;
};

#endif // MAINWINDOW_H
