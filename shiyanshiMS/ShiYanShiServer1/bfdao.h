#ifndef BFDAO_H
#define BFDAO_H

#include <QSqlDatabase>
#include <QSqlQueryModel>
#include <QSqlQuery>
#include <QDebug>
#include <QSqlError>
#include "dbcon.h"
#include "bf.h"

class BfDao
{
public:
    BfDao();
public:
    QSqlDatabase db;
    QString msg;
    Bf bf;
    DBCon dbcon;
    QSqlQueryModel *bfModel;
    bool isOk=false;
public:
    bool bfAdd(Bf bf);
    bool bfDelete(QString sbname);
    bool bfSelect(QString sbname);
    bool bfUpdate(Bf bf);
    bool bfSelectAll();
};

#endif // BFDAO_H
