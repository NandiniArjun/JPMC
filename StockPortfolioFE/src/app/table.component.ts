import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { StockService } from './stock.service';
import {SelectionModel} from '@angular/cdk/collections';

export interface Stock {
    id: number;
    name: string;
    symbol: string;
    previousPrice: number;
    currentPrice: number;
    action?: string;
}

@Component({
  selector: 'stock-table',
  templateUrl: 'table.component.html',
  styles: [`table { width: 100%; }
       mat-icon { cursor: pointer; }
      .mat-sort-header-sorted { color: black; }`
    ]
})

export class TableComponent implements OnInit {

  displayedColumns: string[] = ['symbol', 'name', 'previousPrice', 'currentPrice', 'action', "select"];
  selection = new SelectionModel<Stock>(true, []);
  stock!: Stock;
  stocks: Stock[] = [];
  dataSource = new MatTableDataSource<Stock>(this.stocks);
  
  @ViewChild(MatSort)
    sort!: MatSort;

  constructor(
    private stockService: StockService,
    public dialog: MatDialog
  ) {}

  ngOnInit() {
    this.refreshGrid();
  }

  addItem() {
    if(this.stocks === null) {
      this.stocks = [];
    }
    this.stocks.push({
      id: 0,
      symbol: "<STOCK_SYMBOL>",
      name: "<STOCK_NAME>",
      previousPrice: 0,
      currentPrice: 0,
      action: 'Add'
    });
    this.dataSource = new MatTableDataSource(this.stocks);
  }

  editStock(stock: Stock) {
    const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
      width: '250px',
      data: stock
    });
  }

  deleteRows() {
   this.selection.selected.forEach(
      (stock, index) => { 
            if(stock.id !== 0) {
              this.stockService.delete(stock.id).subscribe(() => {
                this.refreshGrid();
              });
            } else{
              for (var i = 0, len = this.dataSource.data.length; i < len; i++) {
                if(this.dataSource.data[i].action === 'Add' && this.dataSource.data[i].id === 0) {
                  this.dataSource.data.splice(i, 1);
                  break;
                }
              }
            }
      });
      this.dataSource = new MatTableDataSource(this.dataSource.data);
    }
  
 
  refreshGrid() {
    this.stockService.getStocks().subscribe((stocks: Stock[]) => {
      this.stocks = stocks;
      this.dataSource = new MatTableDataSource(stocks);
      this.dataSource.sort = this.sort;
    });
  }

  masterToggle(row: any) {
      if(row !== null) {
        this.selection.select(row);
      }
  }
}

@Component({
  selector: 'dialog-overview-example-dialog',
  templateUrl: 'dialog.html',
})
export class DialogOverviewExampleDialog {
  postId: any;

  constructor(
    public dialogRef: MatDialogRef<DialogOverviewExampleDialog>,
    @Inject(MAT_DIALOG_DATA) public data: Stock,
    private stockService: StockService ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  onUpdateData(stock: Stock) {
    this.stockService.updateStock(stock)
        .subscribe(data => this.postId = data.id);
    this.dialogRef.close();
  }

  onSaveClick(stock: Stock){   
    delete stock.action;
    this.stockService.setStock(stock).subscribe(resp => {
      stock.id = +resp;
    });
    this.dialogRef.close();   
  }
}