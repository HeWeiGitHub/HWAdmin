#ifndef USER_H
#define USER_H
#include <QString>

class User
{
public:
    User();

public:
    void setUname(QString name);
    void setUpass(QString pwd);
    void setUphone(QString phone);
    void setUId(qint8 id);
    void setUtype(QString type);

    qint8 getUid();
    QString getUname();
    QString getUpass();
    QString getUtype();
    QString getUphone();
private:
    qint8 uid;
    QString uname;
    QString utype;
    QString upass;
    QString uphone;
};

#endif // USER_H
