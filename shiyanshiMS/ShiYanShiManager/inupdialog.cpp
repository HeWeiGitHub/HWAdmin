#include "inupdialog.h"
#include "ui_inupdialog.h"

InUpDialog::InUpDialog(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::InUpDialog)
{
    ui->setupUi(this);
    connect(ui->Bup,SIGNAL(clicked()),this,SLOT(pressUp()));
    connect(ui->Badd,SIGNAL(clicked()),this,SLOT(pressIn()));
}

InUpDialog::~InUpDialog()
{
    delete ui;
}

void InUpDialog::pressIn()
{
    Shebei sb;
    sb.setSbId(ui->sbId->text().toInt());
    sb.setSbname(ui->sbName->text());
    sb.setSbtype(ui->sbType->text());
    sb.setSbchandi(ui->sbChandi->text());
    sb.setSbcount(ui->sbCount->text().toInt());
    sb.setSbprovider(ui->sbProvider->text());
    sb.setSbprice(ui->sbPrice->text());
    sb.setSbdate(ui->sbDate->selectedDate().toString("yyyy-MM-dd"));
    sb.setSbdate(ui->sbTimeout->selectedDate().toString("yyyy-MM-dd"));
    emit in(sb);
}

void InUpDialog::pressUp()
{
    Shebei sb;
    sb.setSbId(ui->sbId->text().toInt());
    sb.setSbname(ui->sbName->text());
    sb.setSbtype(ui->sbType->text());
    sb.setSbchandi(ui->sbChandi->text());
    sb.setSbcount(ui->sbCount->text().toInt());
    sb.setSbprovider(ui->sbProvider->text());
    sb.setSbprice(ui->sbPrice->text());
    sb.setSbdate(ui->sbDate->selectedDate().toString("yyyy-MM-dd"));
    sb.setSbdate(ui->sbTimeout->selectedDate().toString("yyyy-MM-dd"));
    emit up(sb);
}

void InUpDialog::closeEvent(QCloseEvent *event)
{

    ui->sbId->setText("");
    ui->sbName->setText("");
    ui->sbPrice->setText("");
    ui->sbChandi->setText("");
    ui->sbCount->setText("");
    ui->sbProvider->setText("");
    ui->sbType->setText("");

    event->accept();

}
