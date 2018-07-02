#ifndef WXDAO_H
#define WXDAO_H

#include <QSqlDatabase>
#include <QSqlQueryModel>
#include <QSqlQuery>
#include <QDebug>
#include <QSqlError>
#include "dbcon.h"
#include "wx.h"

class WxDao
{
public:
    WxDao();
public:
    QSqlDatabase db;
    QString msg;
    Wx wx;
    DBCon dbcon;
    QSqlQueryModel *wxModel;
    bool isOk=false;
public:
    bool wxAdd(Wx wx);
    bool wxDelete(QString sbname);
    bool wxSelect(QString sbname);
    bool wxUpdate(Wx wx);
    bool wxSelectAll();
};

#endif // WXDAO_H
