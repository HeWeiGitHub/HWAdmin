#include "adminlogindlg.h"
#include "ui_adminlogindlg.h"

AdminLoginDlg::AdminLoginDlg(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::AdminLoginDlg)
{
    ui->setupUi(this);
    ui->pass->setEchoMode(QLineEdit::Password);
    connect(ui->Blg,SIGNAL(clicked(bool)),this,SLOT(adminLogin()));
    connect(ui->Bcl,SIGNAL(clicked(bool)),this,SLOT(adminCancel()));
}

AdminLoginDlg::~AdminLoginDlg()
{
    delete ui;
}

void AdminLoginDlg::adminLogin()
{
    emit sendLg(ui->name->text(),ui->pass->text());
}

void AdminLoginDlg::adminCancel()
{
    ui->name->setText("");
    ui->pass->setText("");
}
