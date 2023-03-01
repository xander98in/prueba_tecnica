import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Respuesta, Usuario } from '../../shared/interfaces/usuarios.interface';
import { map, catchError, tap } from 'rxjs/operators';
import { Observable, of } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl:string = environment.baseUrl;
  private _usuario!: Usuario;

  get usuario() {
    return {...this._usuario};
  }

  constructor( private http: HttpClient ) { }

  login( nombre_usuario: string, contrasenia: string ) {

    const url = `${this.baseUrl}/usuarios/login`;
    const body = { nombre_usuario, contrasenia };

    return this.http.post<Respuesta>(url, body)
      .pipe(
        tap( respuesta => {
          console.log(respuesta);
          if(respuesta.estatus === 'OK') {
            localStorage.setItem('id', respuesta.data.id.toString());
          }

        }),
        map( respuesta => respuesta.estatus),
        catchError( err => of(err.error.mensajeUsuario) )
      );

  }

  validarUsuario(): Observable<boolean> {
    const id: string = localStorage.getItem('id') || '-1';
    const iduser: number = +id;
    console.log(typeof iduser);
    console.log(iduser);


    const url = `${this.baseUrl}/usuarios/${iduser}`;

    return this.http.get<Respuesta>(url)
      .pipe(
        map( resp => {
          if( resp.estatus === 'OK') {
            this._usuario = {
              id: resp.data.id!,
              nombre_usuario: resp.data.nombre_usuario,
              correo_electronico: resp.data.correo_electronico,
              contrasenia: resp.data.contrasenia,
              perfil: resp.data.perfil
            }
            localStorage.setItem('id', this._usuario.id!.toString());
            localStorage.setItem('usuario', this._usuario.nombre_usuario);
            localStorage.setItem('perfil', this._usuario.perfil)
            return true;
          }
          return false;
        }),
        catchError( err => of(false) )
      );
  }

  cerrarSesion() {
    localStorage.clear();
  }
}
