package Aereo;

public class Entrata extends Thread{

    private coda economy;
    private coda firstclass;

    private coda salito;

    int i=0;

    public Entrata(coda e,coda f)
    {
        economy=new coda();
        firstclass = new coda();

        this.economy=e;
        this.firstclass=f;

        salito=new coda();
    }
    public void run()
    {
        if(firstclass.size!=0)
        {
         salito.add(firstclass.pop);
         i++;
        }
        else
        {
            salito.add(economy.pop);
        }

    }


    public coda Getsaliti()
    {
    return  salito;
    }
    public int Getnperson()
    {
        return i;
    }

}

