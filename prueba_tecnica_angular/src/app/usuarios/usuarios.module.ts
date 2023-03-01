import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { UsuariosRoutingModule } from './usuarios-routing.module';
import { MenuPrincipalComponent } from './views/menu-principal/menu-principal.component';
import { ListarUsuariosComponent } from './views/listar-usuarios/listar-usuarios.component';
import { CrearUsuarioComponent } from './views/crear-usuario/crear-usuario.component';
import { PrimengModule } from '../primeng/primeng.module';
import { PaginadorComponent } from './components/paginador/paginador.component';


@NgModule({
  declarations: [
    MenuPrincipalComponent,
    ListarUsuariosComponent,
    CrearUsuarioComponent,
    PaginadorComponent
  ],
  imports: [
    CommonModule,
    FlexLayoutModule,
    FormsModule,
    UsuariosRoutingModule,
    PrimengModule,
    ReactiveFormsModule,
  ]
})
export class UsuariosModule { }
