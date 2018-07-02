#include "user.h"

User::User()
{

}

qint8 User::getUid()
{
    return this->uid;
}

QString User::getUname()
{
    return this->uname;
}
QString User::getUpass()
{
    return this->upass;
}
QString User::getUphone()
{
    return this->uphone;
}
QString User::getUtype()
{
    return this->utype;
}

void User::setUId(qint8 id)
{
    this->uid=id;
}

void User::setUname(QString name)
{
    this->uname=name;
}

void User::setUpass(QString pass)
{
    this->upass=pass;
}

void User::setUphone(QString phone)
{
    this->uphone=phone;
}
void User::setUtype(QString type)
{
    this->utype=type;
}

