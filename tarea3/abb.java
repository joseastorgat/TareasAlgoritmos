class abb
{
  String elemento;
  abb izq;
  abb der;

  abb(String o){
    this.elemento=o;
    this.izq=null;
    this.der=null;

  }

  abb(String o, abb izq, abb der){
    this.elemento=o;
    this.izq=izq;
    this.der=der;
  }

}


class NodoPilaABB
{
  abb elemento;
  NodoPilaABB siguiente;

  NodoPilaABB(abb o)
  {
    this.elemento=o;
    this.siguiente=null;
  }

  NodoPilaABB(abb o, NodoPilaABB n)
  {
    this.elemento=o;
    this.siguiente=n;
  }
}


class PilaABB
{
  private NodoPilaABB lista;
  
  public PilaABB(){
    lista=null;
  }

  public void apilar(abb x)
  {
    lista=new NodoPilaABB(x, lista);
  }

  public abb desapilar() // si esta vacia se produce UNDERFLOW
  {
    if (!estaVacia())
    {
      abb x=lista.elemento;
      lista=lista.siguiente;
      return x;
    }
    return null;
  }

  public abb tope()
  {
    if (!estaVacia()) // si esta vacia es un error
    {
      abb x=lista.elemento;
      return x;
    }
    return null;
  }	

  public boolean estaVacia()
  {
    return lista==null;
  }

}



