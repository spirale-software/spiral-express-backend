import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IColi } from 'app/shared/model/coli.model';

type EntityResponseType = HttpResponse<IColi>;
type EntityArrayResponseType = HttpResponse<IColi[]>;

@Injectable({ providedIn: 'root' })
export class ColiService {
  public resourceUrl = SERVER_API_URL + 'api/colis';

  constructor(protected http: HttpClient) {}

  create(coli: IColi): Observable<EntityResponseType> {
    return this.http.post<IColi>(this.resourceUrl, coli, { observe: 'response' });
  }

  update(coli: IColi): Observable<EntityResponseType> {
    return this.http.put<IColi>(this.resourceUrl, coli, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IColi>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IColi[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
