import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Stock } from './table.component';
import { Observable } from 'rxjs/internal/Observable';

@Injectable()
export class StockService {

  readonly API_URL = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  
  getStocks() {
    return this.http.get<any>(`${this.API_URL}/stocks`);
  }

  getStockById(id: number) {
    return this.http.get<any>(`${this.API_URL}/stocks/${id}`);
  }

  setStock(stock: Stock) {
    return this.http.post(`${this.API_URL}/stocks`, stock, {responseType: 'text'});
  }

  updateStock(stock : Stock){
    return this.http.put<any>(`${this.API_URL}/stocks/${stock.id}`, stock);
  }

  delete(id: number){
    return this.http.delete(`${this.API_URL}/stocks/${id}`, {responseType: 'text'});
  }

  getImages() {
    return this.http.get('https://jsonplaceholder.typicode.com/photos?albumId=1');
  }
}
