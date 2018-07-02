#ifndef DBCON_H
#define DBCON_H

#include <QtSql/QSqlDatabase>
#include <QList>
#include <QDebug>

class DBCon
{
public:
    DBCon();
public:
    QSqlDatabase db;
    QString conmsg;
};

#endif // DBCON_H
