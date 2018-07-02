#include "login.h"
#include "ui_login.h"

Login::Login(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::Login)
{
    ui->setupUi(this);
    ui->upass->setEchoMode(QLineEdit::Password);
    connect(ui->Blg,SIGNAL(clicked(bool)),this,SLOT(uLogin()));
    connect(ui->Bcl,SIGNAL(clicked(bool)),this,SLOT(uCancel()));
}

Login::~Login()
{
    delete ui;
}

void Login::uLogin()
{
    emit sendLg(ui->uname->text(),ui->upass->text());
}

void Login::uCancel()
{
    ui->uname->setText("");
    ui->upass->setText("");
}
