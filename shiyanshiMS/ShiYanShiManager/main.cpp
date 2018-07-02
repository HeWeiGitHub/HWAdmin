#include "mainwindow.h"
#include <QApplication>


int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    QStringList path=QCoreApplication::libraryPaths();
    foreach (QString var, path)
    {
        qDebug()<<var;
    }
    MainWindow w;
    w.hide();

    return a.exec();
}
