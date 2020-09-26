import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { EnvoiService } from 'app/entities/envoi/envoi.service';
import { IEnvoi, Envoi } from 'app/shared/model/envoi.model';
import { StatutEnvoi } from 'app/shared/model/enumerations/statut-envoi.model';

describe('Service Tests', () => {
  describe('Envoi Service', () => {
    let injector: TestBed;
    let service: EnvoiService;
    let httpMock: HttpTestingController;
    let elemDefault: IEnvoi;
    let expectedResult: IEnvoi | IEnvoi[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EnvoiService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Envoi(0, currentDate, StatutEnvoi.PRISE_EN_CHARGE, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateCreation: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Envoi', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateCreation: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateCreation: currentDate,
          },
          returnedFromService
        );

        service.create(new Envoi()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Envoi', () => {
        const returnedFromService = Object.assign(
          {
            dateCreation: currentDate.format(DATE_FORMAT),
            statut: 'BBBBBB',
            reference: 'BBBBBB',
            rapportQuai: 'BBBBBB',
            rapportLivraisaon: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateCreation: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Envoi', () => {
        const returnedFromService = Object.assign(
          {
            dateCreation: currentDate.format(DATE_FORMAT),
            statut: 'BBBBBB',
            reference: 'BBBBBB',
            rapportQuai: 'BBBBBB',
            rapportLivraisaon: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateCreation: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Envoi', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
