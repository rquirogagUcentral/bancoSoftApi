package com.bancosoft.ws.rest.Facade;



public interface ICrudServices<T> {
	public T  consutar(T request);
	public boolean  crear(T request);
	public boolean  actualizar(T request);
	public boolean  eliminar(T request);
}
