import { Injectable } from '@angular/core';
import { CanActivate, CanLoad, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../autenticacion/services/auth.service';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ValidarAdministradorGuard implements CanActivate, CanLoad {

  constructor( private authService: AuthService,
    private router: Router ) {}

  canActivate( ): Observable<boolean> | boolean {
    const esAdministrador = localStorage.getItem('perfil');
    if( !(esAdministrador === 'administrador') ) {
      this.router.navigateByUrl('/usuarios/listar')
    }
    return true;
  }
  canLoad( ): Observable<boolean> | boolean {
    const esAdministrador = localStorage.getItem('perfil');
    if( !(esAdministrador === 'administrador') ) {
      this.router.navigateByUrl('/usuarios/listar')
    }
    return true;
  }
}
