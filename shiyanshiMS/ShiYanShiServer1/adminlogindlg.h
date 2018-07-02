#ifndef ADMINLOGINDLG_H
#define ADMINLOGINDLG_H

#include <QDialog>

namespace Ui {
class AdminLoginDlg;
}

class AdminLoginDlg : public QDialog
{
    Q_OBJECT

public:
    explicit AdminLoginDlg(QWidget *parent = 0);
    ~AdminLoginDlg();

private:
    Ui::AdminLoginDlg *ui;

public slots:
    void adminLogin();
    void adminCancel();
signals:
    void sendLg(QString uname,QString upass);
};

#endif // ADMINLOGINDLG_H
