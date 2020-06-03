package main.jobing.mynewjobing.employee;

/*
Класс сотрудника
поле specialty_name - добавил просто для вида, т.к.
я понимаю отображение его названия специальности логично брать по id специальности
либо из базы , либо из нового JSON после обновления данных по сети!
*/

public class MyEmployee
{
    public String f_name;
    public String l_name;
    public String birthday;
    public String avatr_url;
    public Long specialty_id;
    public String specialty_name;

    public MyEmployee()
    {

    }

    public MyEmployee(
            String f_name,
            String l_name,
            String birthday,
            String avatr_url,
            Long specialty_id,
            String specialty_name
    )
    {
        this.f_name = f_name;
        this.l_name = l_name;
        this.birthday = birthday;
        this.avatr_url = avatr_url;
        this.specialty_id = specialty_id;
        this.specialty_name = specialty_name;
    }

}
