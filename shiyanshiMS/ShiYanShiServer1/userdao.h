#ifndef USERDAO_H
#define USERDAO_H

#include "user.h"
#include "dbcon.h"
#include "shebei.h"
#include <QSqlQueryModel>
#include <QSqlQuery>
#include <QSqlError>

class USERDAO
{
public:
    USERDAO();
public:
    QSqlDatabase db;
    QString msg;
    User user;
    DBCon dbcon;
    QSqlQueryModel *uModel;
    bool isOk=false;
public:
    bool uAdd(User user);
    bool uDelete(QString uname);
    bool uSelect(QString uname);
    bool uUpdate(User user);
    bool uSelectAll();
};

#endif // USERDAO_H
