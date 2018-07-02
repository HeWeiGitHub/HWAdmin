#ifndef SBDAO_H
#define SBDAO_H
#include <QSqlDatabase>
#include <QSqlQueryModel>
#include <QSqlQuery>
#include <QDebug>
#include <QSqlError>
#include "dbcon.h"
#include "shebei.h"


class SbDao
{
public:
    SbDao();
public:
    QSqlDatabase db;
    QString msg;
    Shebei sb;
    DBCon dbcon;
    QSqlQueryModel *sbModel;
    bool isOk=false;
public:
    bool sbAdd(Shebei sb);
    bool sbDelete(QString sbname);
    bool sbSelect(QString sbname);
    bool sbUpdate(Shebei sb);
    bool sbSelectAll();
};

#endif // SBDAO_H
