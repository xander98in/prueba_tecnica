import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ValidatorService } from '../../../shared/validator/validator.service';
import { EmailValidatorService } from '../../../shared/validator/email-validator.service';
import { Usuario } from 'src/app/shared/interfaces/usuarios.interface';
import { UsuariosService } from '../../services/usuarios.service';

import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-crear-usuario',
  templateUrl: './crear-usuario.component.html',
  styleUrls: ['./crear-usuario.component.css']
})
export class CrearUsuarioComponent implements OnInit {

  formularioUsuario: FormGroup = this.fb.group({
    nombre_usuario: [ , [ Validators.required, Validators.minLength(2) ] ],
    contrasenia: [ , [ Validators.required, Validators.minLength(8) ] ],
    confirmar_contrasenia: [ , [ Validators.required, Validators.pattern( this.validatorService.contraseniaPattern ) ]],
    correo_electronico: [ , [ Validators.required, Validators.pattern( this.validatorService.emailPattern ) ], [ this.emailValidatorService ]],
    perfil: [ '', Validators.required ]
  }, {
    validators: [ this.validatorService.camposIguales('contrasenia', 'confirmar_contrasenia'), this.validatorService.campoPerfil('perfil') ]
  });

  nombresPerfiles: string[] = [];

  constructor( private fb: FormBuilder,
    private validatorService: ValidatorService,
    private emailValidatorService: EmailValidatorService,
    private usuarioService: UsuariosService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.usuarioService.obtenerNombresPerfiles()
      .subscribe( resp => {
        console.log(resp);
        this.nombresPerfiles = resp;
      })
  }

  get contraseniaErrorMsg(): string{

    const errors = this.formularioUsuario.get('contrasenia')?.errors;
    if(errors?.['required']) {
      return 'La contrasenia es obligatoria';
    }
    if(errors?.['pattern']) {
      return 'La contraseña debe tener minimo ocho caracteres, al menos una mayuscula y una minuscula';
    }
    return '';
  }

  get emailErrorMsg(): string {
    const errors = this.formularioUsuario.get('correo_electronico')?.errors;

    if(errors?.['required']) {
      return 'Email es obligatorio';
    }
    else if (errors?.['pattern']) {
      return 'El valor ingresado no tiene formato de correo';
    }
    else if (errors?.['emailTomado']) {
      return 'El email ya fue tomado';
    }

    return '';
  }

  campoEsValido( campo: string ) {
    return this.formularioUsuario.controls[campo].errors &&
          this.formularioUsuario.controls[campo].touched;
  }

  guardarUsuario() {

    if( this.formularioUsuario.invalid ) {
      this.formularioUsuario.markAllAsTouched();
      return;
    }
    else {
      const usuarioEnviar: Usuario = {
        nombre_usuario: this.formularioUsuario.value.nombre_usuario,
        correo_electronico: this.formularioUsuario.value.correo_electronico,
        contrasenia: this.formularioUsuario.value.confirmar_contrasenia,
        perfil: this.formularioUsuario.value.perfil,
      }

      this.usuarioService.crearUsuario(usuarioEnviar)
        .subscribe( respuesta => {
          const respuestaValida = respuesta.estatus;
          if( respuestaValida === 'CREATED') {
            Swal.fire(
              'Usuario creado!',
              respuesta.mensajeUsuario,
              'success'
            );
            this.router.navigateByUrl('/usuarios/listar');
          }
          if( respuestaValida === 'BAD_REQUEST')  {
            Swal.fire({
              icon: 'error',
              title: 'Error',
              text: respuesta.mensajeUsuario,
              footer: respuesta.errors
            })
          }
        })

    }
    this.formularioUsuario.reset();
    this.formularioUsuario.controls['perfil'].setValue("");
  }

}
