import { NgModule } from '@angular/core';

import {ButtonModule} from 'primeng/button';
import {DropdownModule} from 'primeng/dropdown';
import {InputTextModule} from 'primeng/inputtext';
import {PasswordModule} from 'primeng/password';
import {TabMenuModule} from 'primeng/tabmenu';
import {TableModule} from 'primeng/table';
import {ToolbarModule} from 'primeng/toolbar';

@NgModule({
  exports: [
    ButtonModule,
    DropdownModule,
    InputTextModule,
    PasswordModule,
    TabMenuModule,
    TableModule,
    ToolbarModule,
  ]
})
export class PrimengModule { }
