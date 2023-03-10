import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CrearUsuarioComponent } from './views/crear-usuario/crear-usuario.component';
import { ListarUsuariosComponent } from './views/listar-usuarios/listar-usuarios.component';
import { MenuPrincipalComponent } from './views/menu-principal/menu-principal.component';
import { ValidarAdministradorGuard } from '../guards/validar-administrador.guard';

const routes: Routes = [
  {
    path: '',
    component: MenuPrincipalComponent,
    children: [
      {
        path: 'listar',
        component: ListarUsuariosComponent
      },
      {
        path: 'listar/page/:page',
        component: ListarUsuariosComponent
      },
      {
        path: 'crear',
        component: CrearUsuarioComponent,
        canActivate: [ ValidarAdministradorGuard ],
        canLoad: [ ValidarAdministradorGuard ]
      },
      {
        path: '**',
        redirectTo: 'listar'
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsuariosRoutingModule { }
