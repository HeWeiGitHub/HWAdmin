#include "sededialog.h"
#include "ui_sededialog.h"

SeDeDialog::SeDeDialog(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::SeDeDialog)
{
    ui->setupUi(this);

    connect(ui->Bsel,SIGNAL(clicked()),this,SLOT(PressSel()));
    connect(ui->Bdel,SIGNAL(clicked()),this,SLOT(PressDel()));
    connect(ui->BselWx,SIGNAL(clicked()),this,SLOT(PressSelWx()));
    connect(ui->BdelWx,SIGNAL(clicked()),this,SLOT(PressDelWx()));
    connect(ui->BselBf,SIGNAL(clicked()),this,SLOT(PressSelBf()));
    connect(ui->BdelBf,SIGNAL(clicked()),this,SLOT(PressDelBf()));
}

SeDeDialog::~SeDeDialog()
{
    delete ui;
}

void SeDeDialog::PressSel()
{
    emit sel(ui->sbname->text());
}

void SeDeDialog::PressDel()
{
    emit del(ui->sbname->text());
}

void SeDeDialog::PressSelWx()
{
    emit selWx(ui->sbname->text());
}

void SeDeDialog::PressDelWx()
{
    emit delWx(ui->sbname->text());
}

void SeDeDialog::PressSelBf()
{
    emit selBf(ui->sbname->text());
}

void SeDeDialog::PressDelBf()
{
    emit delBf(ui->sbname->text());
}
