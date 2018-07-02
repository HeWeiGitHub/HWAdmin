#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QDateTime>
#include <QTimer>
#include <QtNetwork>
#include <QtNetwork/QTcpServer>
#include <QtNetwork/QTcpSocket>
#include <QMessageBox>
#include <QJsonObject>
#include <QJsonDocument>
#include <QSqlRecord>
#include <QSqlQueryModel>
#include "adminlogindlg.h"
#include "sbdao.h"
#include "shebei.h"
#include "user.h"
#include "bf.h"
#include "wx.h"
#include "wxdao.h"
#include "bfdao.h"
#include "userdao.h"

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

private:
    Ui::MainWindow *ui;
    AdminLoginDlg *adminloginDlg;
    qint16 blockSize;
    QTcpServer *tcpServer;
    QTcpSocket *tcpSocket;
    QString HostInof;
    bool isOk=false;
    quint16 timeout=2000;
    quint8 delaytime=150;
    quint8 ConCount=0;
    QString admin="";
    QDateTime servDT;
    QString servDTS="";
    QTimer *servTimer;

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();

protected:
    void init();
    QString get_localmachine_ip();
    void delay(quint32 time);


public:
    SbDao sbdao;
    USERDAO userdao;
    WxDao wxdao;
    BfDao bfdao;

public:
    void allUser(QString sql);
    void delUser(QString uname);
    void addUser(User user);

    void allSb(QString sql);
    void getSb(QString sbname);
    void delSb(QString sbname);
    void addSb(Shebei sb);
    void upSb(Shebei sb);

    void allWx(QString sql);
    void getWx(QString sbname);
    void delWx(QString sbname);
    void addWx(Wx wx);
    void upWx(Wx wx);

    void allBf(QString sql);
    void getBf(QString sbname);
    void delBf(QString sbname);
    void addBf(Bf bf);
    void upBf(Bf bf);

    void uLogin(QString uname,QString upass);

private slots:
    void adminlogin(QString uname,QString upass);
    void sendData();
    void sendInfo(QJsonObject json);
    void sendModel(QSqlQueryModel *queryModel,QString sql);
    void sendUserModel(QSqlQueryModel *queryModel,QString sql);
    void sendWxModel(QSqlQueryModel *queryModel,QString sql);
    void sendBfModel(QSqlQueryModel *queryModel,QString sql);
    void recvData();
    void newListen();
    void acceptConnection();
    void disConnection();
    void displayError(QAbstractSocket::SocketError);
    void refreshTime();

    void closeEvent(QCloseEvent *event);


};

#endif // MAINWINDOW_H
