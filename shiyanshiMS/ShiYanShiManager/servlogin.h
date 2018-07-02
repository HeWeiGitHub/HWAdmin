#ifndef SERVLOGIN_H
#define SERVLOGIN_H

#include <QDialog>

namespace Ui {
class ServLogin;
}

class ServLogin : public QDialog
{
    Q_OBJECT

public:
    explicit ServLogin(QWidget *parent = 0);
    ~ServLogin();

private:
    Ui::ServLogin *ui;
public slots:
    void serConnect();
    void uCancel();
signals:
    void servLg(QString ip,quint32 port);
};

#endif // SERVLOGIN_H
