#ifndef USERDLG_H
#define USERDLG_H

#include <QDialog>
#include <QCloseEvent>
#include "user.h"

namespace Ui {
class UserDlg;
}

class UserDlg : public QDialog
{
    Q_OBJECT

public:
    explicit UserDlg(QWidget *parent = 0);
    ~UserDlg();

private:
    Ui::UserDlg *ui;
public slots:
    void uAdd();
    void uCancel();
    void closeEvent(QCloseEvent *event);
signals:
    void sendAdd(User user);

};

#endif // USERDLG_H
