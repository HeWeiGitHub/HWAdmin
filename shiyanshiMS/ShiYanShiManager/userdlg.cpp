#include "userdlg.h"
#include "ui_userdlg.h"

UserDlg::UserDlg(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::UserDlg)
{
    ui->setupUi(this);
    connect(ui->Bok,SIGNAL(clicked()),this,SLOT(uAdd()));
    connect(ui->Bcl,SIGNAL(clicked()),this,SLOT(uCancel()));
}

UserDlg::~UserDlg()
{
    delete ui;
}

void UserDlg::uAdd()
{
    User user;
    user.setUId(ui->uid->text().toInt());
    user.setUname(ui->uname->text());
    user.setUpass(ui->upass->text());
    user.setUphone(ui->uphone->text());
    user.setUtype(QString(ui->utype->currentText()));
    emit sendAdd(user);
}

void UserDlg::uCancel()
{
    ui->uname->setText("");
    ui->upass->setText("");
    ui->uphone->setText("");
    ui->uid->setText("");

}

void UserDlg::closeEvent(QCloseEvent *event)
{

    uCancel();
    event->accept();

}
