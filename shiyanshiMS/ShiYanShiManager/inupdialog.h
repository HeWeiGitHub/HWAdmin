#ifndef INUPDIALOG_H
#define INUPDIALOG_H

#include "shebei.h"
#include <QCloseEvent>
#include <QDialog>

namespace Ui {
class InUpDialog;
}

class InUpDialog : public QDialog
{
    Q_OBJECT

public:
    explicit InUpDialog(QWidget *parent = 0);
    ~InUpDialog();

private:
    Ui::InUpDialog *ui;

public slots:
    void pressUp();
    void pressIn();
    void closeEvent(QCloseEvent *event);
signals:
    void up(Shebei sb);
    void in(Shebei sb);

};

#endif // INUPDIALOG_H
