#include "weixiu.h"
#include "ui_weixiu.h"

WeiXiu::WeiXiu(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::WeiXiu)
{
    ui->setupUi(this);
    connect(ui->Bup,SIGNAL(clicked()),this,SLOT(pressUp()));
    connect(ui->Badd,SIGNAL(clicked()),this,SLOT(pressIn()));
}

WeiXiu::~WeiXiu()
{
    delete ui;
}

void WeiXiu::pressIn()
{
    Wx wx;
    wx.Wxid=ui->Wxid->text().toInt();
    wx.sbname=ui->sbname->text();
    wx.yuanyin=ui->yuanyin->text();
    wx.fuzeren=ui->fuzeren->text().toInt();
    wx.wxdanwei=ui->wxdanwei->text();
    wx.wxdidian=ui->wxdidian->text();
    wx.wxbeizhu=ui->wxbeizhu->toPlainText();
    wx.wxdate=ui->wxdate->selectedDate().toString("yyyy-MM-dd");
    wx.wxshenheren=ui->wxshenheren->text();

    emit inWx(wx);
}

void WeiXiu::pressUp()
{
    Wx wx;
    wx.Wxid=ui->Wxid->text().toInt();
    wx.sbname=ui->sbname->text();
    wx.yuanyin=ui->yuanyin->text();
    wx.fuzeren=ui->fuzeren->text().toInt();
    wx.wxdanwei=ui->wxdanwei->text();
    wx.wxdidian=ui->wxdidian->text();
    wx.wxbeizhu=ui->wxbeizhu->toPlainText();
    wx.wxdate=ui->wxdate->selectedDate().toString("yyyy-MM-dd");
    wx.wxshenheren=ui->wxshenheren->text();

    emit upWx(wx);
}

void WeiXiu::closeEvent(QCloseEvent *event)
{

    ui->Wxid->setText("");
    ui->sbname->setText("");
    ui->wxdanwei->setText("");
    ui->wxdidian->setText("");
    ui->wxbeizhu->setPlainText("");
    ui->wxshenheren->setText("");
    ui->fuzeren->setText("");
    ui->yuanyin->setText("");
    event->accept();

}
