import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IColi, Coli } from 'app/shared/model/coli.model';
import { ColiService } from './coli.service';
import { ColiComponent } from './coli.component';
import { ColiDetailComponent } from './coli-detail.component';
import { ColiUpdateComponent } from './coli-update.component';

@Injectable({ providedIn: 'root' })
export class ColiResolve implements Resolve<IColi> {
  constructor(private service: ColiService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IColi> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((coli: HttpResponse<Coli>) => {
          if (coli.body) {
            return of(coli.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Coli());
  }
}

export const coliRoute: Routes = [
  {
    path: '',
    component: ColiComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'spiralExpressApp.coli.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ColiDetailComponent,
    resolve: {
      coli: ColiResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'spiralExpressApp.coli.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ColiUpdateComponent,
    resolve: {
      coli: ColiResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'spiralExpressApp.coli.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ColiUpdateComponent,
    resolve: {
      coli: ColiResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'spiralExpressApp.coli.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
