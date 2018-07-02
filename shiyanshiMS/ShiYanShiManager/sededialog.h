#ifndef SEDEDIALOG_H
#define SEDEDIALOG_H

#include <QDialog>


namespace Ui {
class SeDeDialog;
}

class SeDeDialog : public QDialog
{
    Q_OBJECT

public:
    explicit SeDeDialog(QWidget *parent = 0);
    ~SeDeDialog();

private:
    Ui::SeDeDialog *ui;

signals:
    void sel(QString data);
    void del(QString data);
    void selWx(QString data);
    void delWx(QString data);
    void selBf(QString data);
    void delBf(QString data);
public slots:
    void PressSel();
    void PressDel();
    void PressSelWx();
    void PressDelWx();
    void PressSelBf();
    void PressDelBf();
};

#endif // SEDEDIALOG_H
