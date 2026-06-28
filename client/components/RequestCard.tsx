"use client";

import { useCallback, useEffect, useReducer, useRef } from "react";
import { Icon } from "@/components/Icon";

const TOTAL_TIME = 15;

type Status = "active" | "accepting" | "dismissed";
type State = { timeLeft: number; status: Status };
type Action = { type: "tick" } | { type: "accept" } | { type: "dismiss" };

function reducer(state: State, action: Action): State {
  switch (action.type) {
    case "tick":
      return { ...state, timeLeft: Math.max(0, state.timeLeft - 1) };
    case "accept":
      return { ...state, status: "accepting" };
    case "dismiss":
      return { ...state, status: "dismissed" };
  }
}

export function RequestCard() {
  const [state, dispatch] = useReducer(reducer, { timeLeft: TOTAL_TIME, status: "active" });
  const { timeLeft, status } = state;
  const acceptTimer = useRef<ReturnType<typeof setTimeout> | null>(null);

  // Countdown only runs while the request is active.
  useEffect(() => {
    if (status !== "active") return;
    const id = setInterval(() => dispatch({ type: "tick" }), 1000);
    return () => clearInterval(id);
  }, [status]);

  // Auto-dismiss when the timer expires.
  useEffect(() => {
    if (timeLeft === 0 && status === "active") dispatch({ type: "dismiss" });
  }, [timeLeft, status]);

  useEffect(() => () => { if (acceptTimer.current) clearTimeout(acceptTimer.current); }, []);

  const handleAccept = useCallback(() => {
    dispatch({ type: "accept" });
    acceptTimer.current = setTimeout(() => dispatch({ type: "dismiss" }), 1200);
  }, []);

  const urgent = timeLeft <= 5;
  const dismissed = status === "dismissed";

  return (
    <div
      id="request-card"
      role="alertdialog"
      aria-label="New trip request"
      className={`z-10 flex w-full max-w-[440px] flex-col overflow-hidden rounded-xl border border-outline-variant/30 shadow-[0_24px_48px_rgba(0,0,0,0.5)] glass-panel transition-all duration-700 ${
        dismissed ? "translate-y-5 scale-95 opacity-0" : "animate-fade-zoom-in"
      }`}
    >
      {/* Countdown progress bar */}
      <div className="h-1.5 w-full overflow-hidden bg-surface-variant/30">
        <div
          className={`h-full origin-left transition-transform duration-1000 ease-linear ${urgent ? "bg-error" : "bg-primary"}`}
          style={{ transform: `scaleX(${timeLeft / TOTAL_TIME})` }}
        />
      </div>

      <div className="space-y-lg p-lg">
        {/* Title & timer */}
        <div className="flex items-start justify-between">
          <div className="space-y-xs">
            <span className="text-label-sm font-bold uppercase tracking-[0.2em] text-primary/80">Incoming Alert</span>
            <h2 className="text-headline-lg font-bold text-on-surface">New Trip Request</h2>
          </div>
          <div
            className={`rounded-full border px-md py-1 ${urgent ? "border-error/20 bg-error/10" : "border-primary/20 bg-primary/10"}`}
          >
            <span className={`text-headline-md font-bold tabular-nums ${urgent ? "text-error" : "text-primary"}`}>
              {timeLeft}s
            </span>
          </div>
        </div>

        {/* Fare & category */}
        <div className="flex items-center justify-between rounded-xl border border-outline-variant/20 bg-surface-container-highest/50 p-md">
          <div className="flex items-center gap-md">
            <div className="flex h-14 w-14 items-center justify-center rounded-xl border border-primary/20 bg-primary/10">
              <Icon name="electric_car" size={32} className="text-primary" />
            </div>
            <div>
              <p className="text-body-lg font-semibold text-on-surface">Velocity Premium</p>
              <div className="flex items-center gap-1 text-on-surface-variant">
                <Icon name="star" size={16} filled className="text-primary" />
                <p className="text-label-sm">4.9 Rating</p>
              </div>
            </div>
          </div>
          <div className="text-right">
            <p className="text-headline-md text-primary">$42.50</p>
            <p className="text-label-sm font-medium text-on-surface-variant">EST. TOTAL</p>
          </div>
        </div>

        {/* Locations */}
        <div className="relative space-y-md px-1">
          <div className="absolute bottom-4 left-[11px] top-4 w-[2px] bg-outline-variant/40" />
          <div className="flex items-start gap-md">
            <div className="z-10 mt-1.5 flex h-5 w-5 items-center justify-center rounded-full border-2 border-primary bg-surface">
              <div className="h-2 w-2 rounded-full bg-primary shadow-[0_0_8px_rgba(198,198,198,0.8)]" />
            </div>
            <div className="flex-1">
              <p className="text-label-sm font-medium text-on-surface-variant">PICKUP • 1.2 MILES AWAY</p>
              <p className="text-body-lg font-semibold text-on-surface">1450 Broadway, Times Square</p>
            </div>
          </div>
          <div className="flex items-start gap-md">
            <div className="z-10 mt-1.5 flex h-5 w-5 items-center justify-center border-2 border-on-surface bg-on-surface">
              <div className="h-2 w-2 bg-surface" />
            </div>
            <div className="flex-1">
              <p className="text-label-sm font-medium text-on-surface-variant">DROPOFF • 12.4 MILE TRIP</p>
              <p className="text-body-lg font-semibold text-on-surface">JFK International Airport, Terminal 4</p>
            </div>
          </div>
        </div>

        {/* Trip specs */}
        <div className="grid grid-cols-2 gap-md border-y border-outline-variant/10 py-base">
          <div className="flex items-center gap-base text-on-surface">
            <Icon name="schedule" className="text-primary" />
            <span className="text-label-md font-semibold">24 min est.</span>
          </div>
          <div className="flex items-center gap-base text-on-surface">
            <Icon name="person" className="text-primary" />
            <span className="text-label-md font-semibold">2 Passengers</span>
          </div>
        </div>

        {/* Actions */}
        <div className="flex flex-col gap-md pt-base">
          <button
            type="button"
            onClick={handleAccept}
            disabled={status !== "active"}
            className={`group flex h-16 w-full items-center justify-center gap-md rounded-xl text-headline-md font-bold shadow-[0_8px_24px_rgba(0,0,0,0.3)] transition-all active:scale-[0.98] disabled:cursor-default ${
              status === "accepting" ? "bg-white text-black" : "bg-primary text-on-primary hover:bg-white"
            }`}
          >
            {status === "accepting" ? (
              <>
                <Icon name="sync" className="animate-spin" /> CONNECTING...
              </>
            ) : (
              <>
                ACCEPT REQUEST
                <Icon name="arrow_forward" className="transition-transform group-hover:translate-x-1" />
              </>
            )}
          </button>
          <button
            type="button"
            onClick={() => dispatch({ type: "dismiss" })}
            disabled={status !== "active"}
            className="h-14 w-full rounded-xl border border-outline-variant bg-transparent text-label-md font-semibold text-on-surface-variant transition-all hover:bg-surface-container-highest/50 active:scale-[0.98] disabled:opacity-50"
          >
            DECLINE
          </button>
        </div>
      </div>
    </div>
  );
}
