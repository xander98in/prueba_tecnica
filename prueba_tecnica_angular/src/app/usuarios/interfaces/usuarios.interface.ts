export interface Usuario {
  id?:                Number;
  nombre_usuario:     string;
  correo_electronico: string;
  contrasenia:        string;
  perfil:             string;
}

export interface Respuesta {
  estatus:               string;
  mensajeUsuario:        string;
  mensajeDesarrolllador: string;
  errors:                any[];
  data:                  Data;
}

export interface Data {
  id:                 number;
  nombre_usuario:     string;
  correo_electronico: string;
  contrasenia:        string;
  perfil:             string;
}

//Interface respuesta pagination
export interface ResponsePagination {
  estatus:               string;
  mensajeUsuario:        string;
  mensajeDesarrolllador: string;
  errors:                any[];
  data:                  DataPage;
}

export interface DataPage {
  content:          Usuario[];
  pageable:         Pageable;
  last:             boolean;
  totalPages:       number;
  totalElements:    number;
  size:             number;
  number:           number;
  sort:             Sort;
  first:            boolean;
  numberOfElements: number;
  empty:            boolean;
}

export interface Pageable {
  sort:       Sort;
  offset:     number;
  pageSize:   number;
  pageNumber: number;
  unpaged:    boolean;
  paged:      boolean;
}

export interface Sort {
  empty:    boolean;
  sorted:   boolean;
  unsorted: boolean;
}
