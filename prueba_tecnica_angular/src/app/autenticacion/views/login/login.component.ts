import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  formularioLogin: FormGroup = this.fb.group({
    nombre_usuario: [ , [Validators.required]],
    contrasenia: [, [Validators.required]]
  })


  constructor( private fb: FormBuilder,
    private router: Router,
    private authService: AuthService
  ) {}

  campoEsValido( campo: string ) {
    return this.formularioLogin.controls[campo].errors &&
          this.formularioLogin.controls[campo].touched;
  }
  login() {

    if( this.formularioLogin.invalid ) {
      this.formularioLogin.markAllAsTouched();
      return;
    }

    const {nombre_usuario, contrasenia} = this.formularioLogin.value;

    this.authService.login(nombre_usuario, contrasenia)
      .subscribe( resp => {
        if( resp === 'OK') {
          this.router.navigateByUrl('/usuarios/listar');
        }
        else {
          Swal.fire('Error', resp, 'error')
        }
      })

    this.formularioLogin.reset();
  }
}
