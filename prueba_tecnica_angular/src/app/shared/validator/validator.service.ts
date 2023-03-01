import { Injectable } from '@angular/core';
import { AbstractControl, ValidationErrors } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class ValidatorService {

  public emailPattern: string = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
  public contraseniaPattern: string = "^(?=\w*\d)(?=\w*[A-Z])(?=\w*[a-z])\S{8,16}$";

  constructor() { }

  camposIguales( campo1: string, campo2: string ) {

    return ( formGroup: AbstractControl ): ValidationErrors | null => {
      const pass1 = formGroup.get(campo1)?.value;
      const pass2 = formGroup.get(campo2)?.value;
      if( pass1 !== pass2 ) {
        formGroup.get(campo2)?.setErrors({ noIguales: true });
        return { noIguales: true }
      }

      formGroup.get(campo2)?.setErrors(null);

      return null;
    }
  }

  campoPerfil( valuePerfil: string ) {

    return ( formGroup: AbstractControl ): ValidationErrors | null => {
      const perfil = formGroup.get(valuePerfil)?.value;
      if(perfil === "") {
        formGroup.get(valuePerfil)?.setErrors({ campoVacio: true });
        return { campoVacio: true }
      }

      formGroup.get(valuePerfil)?.setErrors(null);
      return null;
    }
  }
}
