import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEnvoi, Envoi } from 'app/shared/model/envoi.model';
import { EnvoiService } from './envoi.service';
import { EnvoiComponent } from './envoi.component';
import { EnvoiDetailComponent } from './envoi-detail.component';
import { EnvoiUpdateComponent } from './envoi-update.component';

@Injectable({ providedIn: 'root' })
export class EnvoiResolve implements Resolve<IEnvoi> {
  constructor(private service: EnvoiService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEnvoi> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((envoi: HttpResponse<Envoi>) => {
          if (envoi.body) {
            return of(envoi.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Envoi());
  }
}

export const envoiRoute: Routes = [
  {
    path: '',
    component: EnvoiComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'spiralExpressApp.envoi.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EnvoiDetailComponent,
    resolve: {
      envoi: EnvoiResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'spiralExpressApp.envoi.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EnvoiUpdateComponent,
    resolve: {
      envoi: EnvoiResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'spiralExpressApp.envoi.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EnvoiUpdateComponent,
    resolve: {
      envoi: EnvoiResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'spiralExpressApp.envoi.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
