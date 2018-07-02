#ifndef LOGIN_H
#define LOGIN_H

#include <QDialog>

namespace Ui {
class Login;
}

class Login : public QDialog
{
    Q_OBJECT

public:
    explicit Login(QWidget *parent = 0);
    ~Login();

private:
    Ui::Login *ui;
public slots:
    void uLogin();
    void uCancel();
signals:
    void sendLg(QString uname,QString upass);
};

#endif // LOGIN_H
