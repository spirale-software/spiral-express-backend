import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEnvoi } from 'app/shared/model/envoi.model';

type EntityResponseType = HttpResponse<IEnvoi>;
type EntityArrayResponseType = HttpResponse<IEnvoi[]>;

@Injectable({ providedIn: 'root' })
export class EnvoiService {
  public resourceUrl = SERVER_API_URL + 'api/envois';

  constructor(protected http: HttpClient) {}

  create(envoi: IEnvoi): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(envoi);
    return this.http
      .post<IEnvoi>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(envoi: IEnvoi): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(envoi);
    return this.http
      .put<IEnvoi>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IEnvoi>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IEnvoi[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(envoi: IEnvoi): IEnvoi {
    const copy: IEnvoi = Object.assign({}, envoi, {
      dateCreation: envoi.dateCreation && envoi.dateCreation.isValid() ? envoi.dateCreation.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateCreation = res.body.dateCreation ? moment(res.body.dateCreation) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((envoi: IEnvoi) => {
        envoi.dateCreation = envoi.dateCreation ? moment(envoi.dateCreation) : undefined;
      });
    }
    return res;
  }
}
