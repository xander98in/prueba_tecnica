import { Injectable } from '@angular/core';
import { AsyncValidator, AbstractControl, ValidationErrors } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map, delay } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class EmailValidatorService implements AsyncValidator {

  constructor( private http: HttpClient ) { }

  validate( control: AbstractControl ): Observable<ValidationErrors | null> {
    const email = control.value;
    return this.http.get<any>(`http://localhost:8080/api/email/${ email }`)
      .pipe(
        delay(3000),
        map( resp => {
          return (resp.data === null)
            ? null
            : {emailTomado: true}
        })
      )
  }
}
