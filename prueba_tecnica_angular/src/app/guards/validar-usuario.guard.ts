import { Injectable } from '@angular/core';
import { CanActivate, CanLoad, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../autenticacion/services/auth.service';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ValidarUsuarioGuard implements CanActivate, CanLoad {

  constructor( private authService: AuthService,
    private router: Router ) {}

  canActivate( ): Observable<boolean> | boolean {
    return this.authService.validarUsuario()
      .pipe(
        tap( valid => {
          if (!valid) {
            this.router.navigateByUrl('/login');
          }
        })
      );
  }
  canLoad( ): Observable<boolean> | boolean {
    return this.authService.validarUsuario()
      .pipe(
        tap( valid => {
          if (!valid) {
            this.router.navigateByUrl('/login');
          }
        })
      );
  }
}
