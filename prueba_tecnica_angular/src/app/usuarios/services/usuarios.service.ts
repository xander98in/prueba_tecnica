import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, of } from 'rxjs';
import { Respuesta, Usuario, ResponsePagination } from 'src/app/shared/interfaces/usuarios.interface';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UsuariosService {

  private baseURL = environment.baseUrl;

  constructor( private http: HttpClient ) { }

  crearUsuario(Usuario: Usuario): Observable<Respuesta> {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };
    return this.http.post<Respuesta>(`${ this.baseURL }/usuarios`, Usuario, httpOptions)
      .pipe(
        map( resp => resp),
        catchError( err => of(err.error))
      );
  }

  obtenerUsuariosPaginados(page: number): Observable<ResponsePagination>{
    return this.http.get<ResponsePagination>(`${ this.baseURL }/usuarios/page/${ page }`)
      .pipe(
        map( resp => resp),
        catchError( err => of(err.error))
      );
  }

  obtenerNombresPerfiles(): Observable<string[] | any> {
    return this.http.get<Respuesta>(`${this.baseURL}/perfil`)
      .pipe(
        map(
          resp => resp.data
        ),
        catchError( err => of(err.error))
      )
  }
}
