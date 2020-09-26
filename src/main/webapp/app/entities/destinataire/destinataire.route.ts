import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDestinataire, Destinataire } from 'app/shared/model/destinataire.model';
import { DestinataireService } from './destinataire.service';
import { DestinataireComponent } from './destinataire.component';
import { DestinataireDetailComponent } from './destinataire-detail.component';
import { DestinataireUpdateComponent } from './destinataire-update.component';

@Injectable({ providedIn: 'root' })
export class DestinataireResolve implements Resolve<IDestinataire> {
  constructor(private service: DestinataireService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDestinataire> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((destinataire: HttpResponse<Destinataire>) => {
          if (destinataire.body) {
            return of(destinataire.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Destinataire());
  }
}

export const destinataireRoute: Routes = [
  {
    path: '',
    component: DestinataireComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'spiralExpressApp.destinataire.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DestinataireDetailComponent,
    resolve: {
      destinataire: DestinataireResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'spiralExpressApp.destinataire.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DestinataireUpdateComponent,
    resolve: {
      destinataire: DestinataireResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'spiralExpressApp.destinataire.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DestinataireUpdateComponent,
    resolve: {
      destinataire: DestinataireResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'spiralExpressApp.destinataire.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
