import { Component, OnInit } from '@angular/core';
import { DataPage, Usuario} from 'src/app/shared/interfaces/usuarios.interface';
import { UsuariosService } from '../../services/usuarios.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-listar-usuarios',
  templateUrl: './listar-usuarios.component.html',
  styleUrls: ['./listar-usuarios.component.css']
})
export class ListarUsuariosComponent implements OnInit {

  listaUsuarios: Usuario[] = [];
  paginador!: DataPage;
  statuses!: any[];

  constructor(
    private usuariosService: UsuariosService,
    private activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {

    this.statuses = [
      {label: 'Administrador', value: 'administrador'},
      {label: 'Invitado', value: 'invitado'},

  ]
    this.activatedRoute.paramMap
      .subscribe( params => {
        let page : number = +params.get('page')! | 0;

        if(!page) {
          page = 0;
        }
        this.usuariosService.obtenerUsuariosPaginados(page)
          .subscribe( resp => {
            console.log(resp.data.content);
            this.listaUsuarios = resp.data.content;
            this.paginador = resp.data;
          })
      });
  }

}
