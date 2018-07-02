#include "servlogin.h"
#include "ui_servlogin.h"

ServLogin::ServLogin(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::ServLogin)
{
    ui->setupUi(this);
    connect(ui->Blg,SIGNAL(clicked(bool)),this,SLOT(serConnect()));
    connect(ui->Bcl,SIGNAL(clicked(bool)),this,SLOT(uCancel()));
}

ServLogin::~ServLogin()
{
    delete ui;
}

void ServLogin::serConnect()
{
    emit servLg(ui->ip->text(),ui->port->text().toInt());
}

void ServLogin::uCancel()
{
    ui->ip->setText("");
    ui->port->setText("");
}
