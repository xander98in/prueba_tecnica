import { Component, OnInit } from '@angular/core';
import {MenuItem} from 'primeng/api';
import { AuthService } from '../../../autenticacion/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu-principal',
  templateUrl: './menu-principal.component.html',
  styleUrls: ['./menu-principal.component.css']
})
export class MenuPrincipalComponent implements OnInit {

  items: MenuItem[] = [];

  esAdministrador: boolean = false;
  perfil: string | null = '';
  nombre_usuario: string | null = '';

  constructor( private authService: AuthService,
    private router: Router ){}

  ngOnInit() {

    this.nombre_usuario = localStorage.getItem('usuario');
    this.perfil = localStorage.getItem('perfil');
    if( this.perfil === 'administrador' )
    {
      this.esAdministrador = true;
    }
    console.log('es administrador', this.esAdministrador);

    this.items = [
      {label: 'LISTA DE USUARIOS', routerLink: "/usuarios/listar"},
      {label: 'CREAR USUARIO', routerLink: "/usuarios/crear", disabled: !this.esAdministrador}
    ];
  }

  cerrarSesion() {
    this.authService.cerrarSesion();
    this.router.navigateByUrl('/login')
  }
}
