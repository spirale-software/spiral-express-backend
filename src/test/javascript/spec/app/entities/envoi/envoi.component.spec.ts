import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SpiralExpressTestModule } from '../../../test.module';
import { EnvoiComponent } from 'app/entities/envoi/envoi.component';
import { EnvoiService } from 'app/entities/envoi/envoi.service';
import { Envoi } from 'app/shared/model/envoi.model';

describe('Component Tests', () => {
  describe('Envoi Management Component', () => {
    let comp: EnvoiComponent;
    let fixture: ComponentFixture<EnvoiComponent>;
    let service: EnvoiService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SpiralExpressTestModule],
        declarations: [EnvoiComponent],
      })
        .overrideTemplate(EnvoiComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EnvoiComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EnvoiService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Envoi(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.envois && comp.envois[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
