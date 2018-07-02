#include "baofei.h"
#include "ui_baofei.h"

BaoFei::BaoFei(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::BaoFei)
{
    ui->setupUi(this);
    connect(ui->Bup,SIGNAL(clicked()),this,SLOT(pressUp()));
    connect(ui->Badd,SIGNAL(clicked()),this,SLOT(pressIn()));
}

BaoFei::~BaoFei()
{
    delete ui;
}

void BaoFei::pressIn()
{
    Bf bf;
    bf.bfid=ui->bfid->text().toInt();
    bf.sbname=ui->sbname->text();
    bf.sbtype=ui->sbtype->text();
    bf.fuzeren=ui->fuzeren->text();
    bf.bfbeizhu=ui->bfbeizhu->toPlainText();
    bf.bfdate=ui->bfdate->selectedDate().toString("yyyy-MM-dd");
    bf.cunfangdidian=ui->cunfangdidian->text();
    bf.bfshenheren=ui->bfshenheren->text();

    emit inBf(bf);
}

void BaoFei::pressUp()
{
    Bf bf;
    bf.bfid=ui->bfid->text().toInt();
    bf.sbname=ui->sbname->text();
    bf.sbtype=ui->sbtype->text();
    bf.fuzeren=ui->fuzeren->text();
    bf.bfbeizhu=ui->bfbeizhu->toPlainText();
    bf.bfdate=ui->bfdate->selectedDate().toString("yyyy-MM-dd-hh");
    bf.cunfangdidian=ui->cunfangdidian->text();
    bf.bfshenheren=ui->bfshenheren->text();
    emit upBf(bf);
}

void BaoFei::closeEvent(QCloseEvent *event)
{

    ui->bfid->setText("");
    ui->sbname->setText("");
    ui->sbtype->setText("");
    ui->fuzeren->setText("");
    ui->bfbeizhu->setPlainText("");
    ui->cunfangdidian->setText("");
    ui->bfshenheren->setText("");
    event->accept();

}
