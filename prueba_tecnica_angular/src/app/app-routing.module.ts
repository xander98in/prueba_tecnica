import { NgModule } from '@angular/core';
import { RouterModule, Routes, CanLoad } from '@angular/router';
import { ValidarUsuarioGuard } from './guards/validar-usuario.guard';

const routes: Routes = [
  {
    path: 'usuarios',
    loadChildren: () => import('./usuarios/usuarios.module').then(m => m.UsuariosModule),
    canActivate: [ ValidarUsuarioGuard ],
    canLoad: [ ValidarUsuarioGuard ]
  },
  {
    path: 'login',
    loadChildren: () => import('./autenticacion/autenticacion.module').then(m => m.AutenticacionModule)
  },
  {
    path: '**',
    redirectTo: 'login'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
