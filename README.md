# MultiTouch Application
This application shows the demostration of multi-touch in Android Platform.
Here no external libraries were used.

The project structure is divided into 3 folders:
  - activity
  - custom
  - constants
  
Activity folder consist of necessary activites required.

Custom folder consist of custom views ie Custom MultiTouch View.

Constants folder consist of constants that are used in the application.

# Implementation
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        ...
        int maskedAction = event.getActionMasked();

        switch (maskedAction) {

        case MotionEvent.ACTION_DOWN:
        case MotionEvent.ACTION_POINTER_DOWN: {
            break;
        }
        case MotionEvent.ACTION_MOVE: { 
            break;
        }
        case MotionEvent.ACTION_UP:
        case MotionEvent.ACTION_POINTER_UP:
        case MotionEvent.ACTION_CANCEL: {
            break;
        }
        }
        invalidate();
        return true;
    }
