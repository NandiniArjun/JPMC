import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatListModule } from '@angular/material/list';
import { MatCheckboxModule } from '@angular/material/checkbox';

import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatBadgeModule } from '@angular/material/badge';

import { TableComponent, DialogOverviewExampleDialog } from './table.component';
import { AppComponent } from './app.component';
import { StockService } from './stock.service';
import { DemoMaterialModule } from '../material-modules';


@NgModule({
  imports:      [ 
    MatCheckboxModule, DemoMaterialModule, BrowserModule, FormsModule, HttpClientModule, BrowserAnimationsModule, MatTableModule, MatIconModule, MatDialogModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatListModule, MatButtonToggleModule, MatBadgeModule
  ],
  declarations: [ AppComponent, TableComponent, DialogOverviewExampleDialog ],
  providers: [StockService],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
